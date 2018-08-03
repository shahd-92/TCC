package com.udacity.shahd.tcc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class activity_maps extends AppCompatActivity implements OnMapReadyCallback {

    static LatLng mRegion ;
    private GoogleMap mMap;
    double lat, lont=0;
    ArrayList<Container> containers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d(getClass().getName(), "onCreate");
        containers =  new ArrayList<Container>();
        Container c1 = new Container(1, "39.895504","21.416422", "mina",  "c-m-1",80);
        Container c2 = new Container(4, "39.901738","21.413705", "mina",  "c-m-2",40);
        Container c3 = new Container(5, "39.891728","21.412058", "mina",  "c-m-3",10);
//        Container c4 = new Container(2, "21.4149474","39.8997368", "arafat",  "c-a-1",50);
//        Container c5 = new Container(3, "21.4149474","39.8997368", "muzdalifah",  "c-z-1",10);
        containers.add(c1);
        containers.add(c2);
        containers.add(c3);

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            Intent intent = this.getIntent();
            if (intent != null) {
                int index = intent.getIntExtra("index", 0);
//                mContainers = ContainersList.containers;
//                containers =getIntent().getParcelableExtra("container");
//                containers.add()
//                Container container = containers;
//                container = getIntent().getParcelableExtra("container");

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
            if(lat!=0 && lont!=0){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q="+lat+","+ lont));
            startActivity(intent);
            return true;
            }
            else {
                Toast.makeText(this, "no marker selected!!", Toast.LENGTH_LONG).show();

            }
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            Log.d("ContainerName2: ", container.getContainerName());
            lat = Double.valueOf(container.getLat());
             lont = Double.valueOf(container.getLont());
//
            Log.d("ContainersList", "lat: " + lat + ", lont: " + lont);
            mRegion = new LatLng(lat, lont);
            if (container.getStatus() <= 25)
                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_trash_small)));
            else if (container.getStatus() < 60 && container.getStatus() > 25)
                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_trash_small)));
            else if (container.getStatus() >= 60)
                mMap.addMarker(new MarkerOptions().position(mRegion).title("Mina").icon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash_small)));
//
        }
            // Add a marker in Sydney, Australia, and move the camera.
            LatLngBounds mina = new LatLngBounds(
                    new LatLng(21.421479, 39.867937), new LatLng(21.428828, 39.895096));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mina.getCenter(), 12));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                lat=marker.getPosition().latitude;
                lont=marker.getPosition().longitude;
                return true;
            }
        });
    }
}
