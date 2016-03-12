package com.example.jaybhatt.bildiaro;

import android.location.Address;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by prate_000 on 3/12/2016.
 */
public class GeoSearchResult {
    private Address address;

    public GeoSearchResult(Address address)
    {
        this.address = address;
    }

    public String getAddress(){

        String display_address = "";

        display_address += address.getAddressLine(0) + "\n";

        for(int i = 1; i < address.getMaxAddressLineIndex(); i++)
        {
            display_address += address.getAddressLine(i) + ", ";
        }

        display_address = display_address.substring(0, display_address.length() - 2);

        return display_address;
    }

    public LatLng getLatLng()
    {
        LatLng lt = new LatLng(address.getLatitude(), address.getLongitude());
        return  lt;
    }


    public String toString(){
        String display_address = "";

        if(address.getFeatureName() != null)
        {
            display_address += address + ", ";
        }

        for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
        {
            display_address += address.getAddressLine(i);
        }

        return display_address;
    }

}
