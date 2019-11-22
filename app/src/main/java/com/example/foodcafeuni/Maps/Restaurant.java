package com.example.foodcafeuni.Maps;

import android.location.Location;

public class Restaurant {
    public String id ;
    public int Imgae ;
    public String Name;
    public Location location;

    public Restaurant() {
    }

    public Restaurant(String id, int imgae, String name, Double lat , Double lag) {
        this.id=id;
        Imgae = imgae;
        Name = name;
        location = new Location(name);
        location.setLongitude(lag);
        location.setLatitude(lat);
    }
}
