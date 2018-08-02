package com.udacity.shahd.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContainersMap extends FragmentActivity implements OnMapReadyCallback {

    static LatLng mRegion ;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d(getClass().getName(), "onCreate");
        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            Intent intent = this.getIntent();
            if (intent != null) {
                int index = intent.getIntExtra("index", 0);
                Container container = getIntent().getParcelableExtra("container");
                Log.d("ContainerName: ", container.getContainerName());
                double lit = Double.valueOf(container.getLat());
                double lont = Double.valueOf(container.getLont());

                Log.d("ContainersList", "lit: " + lit +", lont: "+lont);
                mRegion = new LatLng(lit, lont);
//                int id = intent.getIntExtra("id", 0);
                Log.e("containers_map", "index: " + index);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mRegion));
    }
}


//@Override
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.containers_map);
//    Log.d(getClass().getName(), "onCreate");
//    if (savedInstanceState == null) {
//        // During initial setup, plug in the details fragment.
//        Intent intent = this.getIntent();
//        if (intent != null) {
//            int index = intent.getIntExtra("index", 0);
////                int id = intent.getIntExtra("id", 0);
//            Log.e("containers_map", "index: " + index);
//        }
//    }
//}