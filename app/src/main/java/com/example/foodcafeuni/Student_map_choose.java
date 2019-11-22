package com.example.foodcafeuni;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodcafeuni.Maps.MylocationListener;
import com.example.foodcafeuni.Maps.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Student_map_choose extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button V_btn_Search ;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_map_choose);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LoadRestaurant();
        V_btn_Search = findViewById(R.id.A_btn_Search);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CheckPremmison();

    }
    final   int REQUEST_CODE_ASK_PERMISSIONS=10;
    void  CheckPremmison(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
        Getlocation();
    }
    private void Getlocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MylocationListener myLocationListener = new MylocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3,myLocationListener);

        MyThread myThread = new MyThread();
        myThread.start();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Getlocation();
                }else{
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        }
    }
    Location OldLocation ;
    public class MyThread extends Thread{
        MyThread(){
            OldLocation = new Location("zero");
            OldLocation.setLatitude(0);
            OldLocation.setLongitude(0);
        }
        @Override
        public void run(){
            while (true){
                if(OldLocation.distanceTo(MylocationListener.location)==0){
                    continue;
                }
                OldLocation=MylocationListener.location;
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMap.clear();
                            // Add a marker in Sydney and move the camera
                            LatLng sydney = new LatLng(MylocationListener.location.getLatitude(), MylocationListener.location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in MYlocation").icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_student_male_filled_30px)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

                            for(int x = 0; x < listRes.size(); ++x)
                            {
                                restaurant = listRes.get(x);
                                LatLng rest = new LatLng(restaurant.location.getLatitude(), restaurant.location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(rest).title(restaurant.Name).icon(BitmapDescriptorFactory.fromResource(restaurant.Imgae)));
                            }

                            V_btn_Search.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    boolean f =true;
                                    for(int x = 0; x < listRes.size(); ++x)
                                    {
                                        restaurant = listRes.get(x);
                                        LatLng rest = new LatLng(restaurant.location.getLatitude(), restaurant.location.getLongitude());
                                        mMap.addMarker(new MarkerOptions().position(rest).title(restaurant.Name).icon(BitmapDescriptorFactory.fromResource(restaurant.Imgae)));
                                        if(restaurant.location.distanceTo(MylocationListener.location)<222){
                                           Intent obj = new Intent(Student_map_choose.this,Student_Home.class);
                                          obj.putExtra("ID", restaurant.id);
                                          obj.putExtra("Name",restaurant.Name);
                                          startActivity(obj);
                                            Toast.makeText(Student_map_choose.this, "sadasd", Toast.LENGTH_SHORT).show();
                                            f=false;
                                            break;
                                        }
                                    }
                                    if(f==true) {
                                        Toast.makeText(Student_map_choose.this, "Unserved area", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                    Thread.sleep(1000);
                }
                catch (Exception e){

                }
            }
        }
    }
    ArrayList<Restaurant> listRes = new ArrayList<>();

    void  LoadRestaurant (){
        listRes.add(new Restaurant("1",R.drawable.icons8_restaurant_filled_30px,"cafe 1",32.541436,35.854372));
        listRes.add(new Restaurant("2",R.drawable.icons8_restaurant_filled_30px,"cafe 2",32.534882,35.857701));
        listRes.add(new Restaurant("3",R.drawable.icons8_restaurant_filled_30px,"cafe 3",32.537501,35.854945));
        listRes.add(new Restaurant("4",R.drawable.icons8_restaurant_filled_30px,"cafe 4",32.537686,35.852301));
        listRes.add(new Restaurant("5",R.drawable.icons8_restaurant_filled_30px,"cafe 5",32.559421,35.863991));


       // 32.559421, 35.863991



    }

}
