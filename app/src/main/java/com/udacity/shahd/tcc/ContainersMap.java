package com.udacity.shahd.tcc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ContainersMap extends AppCompatActivity implements OnMapReadyCallback {

    static LatLng mRegion ;
    private GoogleMap mMap;
    Container container;
    ArrayList<Container> mContainers;
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
                mContainers = ContainersList.containers;
//                containers =  new ArrayList<Container>();
//                containers =getIntent().getParcelableExtra("container");
//                containers.add()
//                Container container = containers;
                 container = getIntent().getParcelableExtra("container");

//                int id = intent.getIntExtra("id", 0);
                Log.e("containers_map", "index: " + index);
            }
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.map, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.direction) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q="+container.getLat()+","+ container.getLont()));
                startActivity(intent);                return true;
            }

            return super.onOptionsItemSelected(item);
        }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        for (int i = 0; i < mContainers.size(); i++)
//        {
//            Container container =mContainers.get(i);
            Log.d("ContainerName2: ", container.getContainerName());
            double lit = Double.valueOf(container.getLat());
            double lont = Double.valueOf(container.getLont());
//
//            Log.d("ContainersList", "lit: " + lit +", lont: "+lont);
            mRegion = new LatLng(lit, lont);
//            if(container.getStatus()<=25)
//                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_trash_small)));
//            else if(container.getStatus()<60 && container.getStatus()>25)
//                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_trash_small)));
//            else if(container.getStatus()>=60)
                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash_small)));
//
//        }
        // Add a marker in Sydney, Australia, and move the camera.
         LatLngBounds mina = new LatLngBounds(
                new LatLng(21.421479, 39.867937), new LatLng(21.428828, 39.895096));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mina.getCenter(), 12));
    }
}

//(39.895096, 21.428828), new LatLng(39.867937, 21.421479));
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