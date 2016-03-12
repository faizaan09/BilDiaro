package com.example.jaybhatt.bildiaro;

/**
 * Created by Jay Bhatt on 04-03-2016.
 */
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by Faizaan on 2/18/2016.
 */
@Table
public class Photo {
    @Ignore
    private static Long idCount = 0L;
    private Long id;
    private String caption;
    private Date timestamp;
    private String filePath;
    private int isFav;
    private long loc_long;
    private long loc_lat;
    private Album album;

    public Photo(String caption, Date timestamp, String filePath, long loc_long, long loc_lat, Album album) {
        id = ++idCount;
        this.caption = caption;
        this.timestamp = timestamp;
        this.filePath = filePath;
        this.loc_long = loc_long;
        this.loc_lat = loc_lat;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
