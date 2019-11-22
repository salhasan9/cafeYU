package com.example.foodcafeuni.Maps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MylocationListener implements LocationListener {
public static Location location;


    public MylocationListener() {
        location = new Location("zero");
        location.setLatitude(0);
        location.setLongitude(0);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
