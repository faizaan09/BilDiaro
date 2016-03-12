package com.example.jaybhatt.bildiaro;

/**
 * Created by Jay Bhatt on 04-03-2016.
 */import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

/**
 * Created by Faizaan on 2/18/2016.
 */
@Table
public class Album {
    @Ignore
    static Long idCount = 0L;
    private Long id;
    private String title;
    private int numOfPhotos;
    private long loc_lat;
    private long loc_long;
    private User owner;

    Album ()
    {

    }

    public Album(int numOfPhotos, String title, long loc_lat, long loc_long, User owner) {
        id = ++idCount;
        this.numOfPhotos = numOfPhotos;
        this.title = title;
        this.loc_lat = loc_lat;
        this.loc_long = loc_long;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfPhotos() {
        return numOfPhotos;
    }

    public void setNumOfPhotos(int numOfPhotos) {
        this.numOfPhotos = numOfPhotos;
    }

    public long getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(long loc_lat) {
        this.loc_lat = loc_lat;
    }

    public long getLoc_long() {
        return loc_long;
    }

    public void setLoc_long(long loc_long) {
        this.loc_long = loc_long;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}