package com.udacity.shahd.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ContainersRegions extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.containers_region);
    }



    public ContainersRegions() {
    }

    public void mina_region(View view) {

        Intent intent = new Intent(this, ContainersList.class).putExtra("index", 1);
        startActivity(intent);
    }

    public void arafat_region(View view) {

        Intent intent = new Intent(this, ContainersRegions.class).putExtra("index", 2);
        startActivity(intent);
    }

    public void muzdalifah_region(View view) {

        Intent intent = new Intent(this, ContainersRegions.class).putExtra("index", 3);
        startActivity(intent);
    }



//    @Override
//    public void onListFragmentInteractionContainers(Container item, int tag) {
//        Intent intent = new Intent();
//        intent.setClass(this, ContainersMap.class);
//
//        intent.putExtra("Tool",  item);
//
//        intent.putExtra("index", tag);
//        startActivity(intent);
//    }
}
