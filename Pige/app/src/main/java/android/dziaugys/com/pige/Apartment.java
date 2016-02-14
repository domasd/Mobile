package android.dziaugys.com.pige;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Domas on 2015-12-01.
 */
public class Apartment implements Serializable {

    public Apartment(int id,String title, double cost, double latitude, double longitude, boolean isStarred, boolean isFlat, String contacts) {
        this.cost = cost;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.starred = isStarred;
        this.flat = isFlat;
        this.contact = contacts;
    }

    private int id;

    private String title;

    public List<Bitmap> images;

    private double cost;

    private double latitude;

    private double longitude;

    private String street;

    private String houseNumber;

    private String city;

    private boolean starred;

    private boolean flat;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String contact;

    public boolean isFlat() {
        return flat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean stared) {
        this.starred = stared;
    }
}
