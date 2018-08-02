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

public class activity_maps extends FragmentActivity implements OnMapReadyCallback {

    static LatLng mRegion ;
//    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap mMap;
    private int mIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = this.getIntent();
        if (intent != null) {
            mIndex = intent.getIntExtra("index", 0);
            Log.e("ContainersList", "index: " + mIndex);
            Log.e("ContainersList2", "index: " + mIndex);
//            ,new Container(0, "0","0","region","containerName", 0)
            Container container = getIntent().getParcelableExtra("container");

            Log.d("ContainerName: ", container.getContainerName());
            int lit = Integer.valueOf(container.getLat());
            int lont = Integer.valueOf(container.getLont());

            Log.d("ContainersList", "lit: " + lit +", lont: "+lont);
            mRegion = new LatLng(lit, lont);
            SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map));
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}