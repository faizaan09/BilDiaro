package com.example.jaybhatt.bildiaro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddImageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
    private ImageView geo_autocomplete_clear;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private ImageView image;

    public AddImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddImageFragment newInstance(String param1, String param2) {
        AddImageFragment fragment = new AddImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_image, container, false);
        image = (ImageView) view.findViewById(R.id.img_new);
        String imageUri = getArguments().getString("IMAGE_URI");
        File imgFile = new  File(imageUri);
        long loc_lat=0,loc_long=0;
        if(imgFile.exists()) {
            String latLong = new String();
            try {
                ExifInterface exifInterface = new ExifInterface(imageUri);
                loc_lat = Long.parseLong(exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
                loc_long = Long.parseLong(exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
                if (exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF).equalsIgnoreCase("N"))
                    loc_lat *= -1;
                if (exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF).equalsIgnoreCase("W"))
                    loc_long *= -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Photo photo = new Photo("", null, imageUri, loc_long, loc_lat, null);
            photo.save();

            Bitmap myBitmap = BitmapFactory.decodeFile(new File(photo.getFilePath()).getAbsolutePath());
            image.setImageBitmap(myBitmap);


        }



            geo_autocomplete_clear = (ImageView) view.findViewById(R.id.geo_autocomplete_clear);

            geo_autocomplete = (DelayAutoCompleteTextView) view.findViewById(R.id.geo_autocomplete);
            geo_autocomplete.setThreshold(THRESHOLD);
            geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(getActivity())); // 'this' is Activity instance

            geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                    geo_autocomplete.setText(result.getAddress());
                    Toast.makeText(getActivity(), result.getLatLng().latitude + " " + result.getLatLng().longitude, Toast.LENGTH_LONG).show();
                }
            });

            geo_autocomplete.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        geo_autocomplete_clear.setVisibility(View.VISIBLE);
                    } else {
                        geo_autocomplete_clear.setVisibility(View.GONE);
                    }
                }
            });


        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                geo_autocomplete.setText("");
            }
        });
        return view;
    }



    public void onSave(View view)
    {

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
