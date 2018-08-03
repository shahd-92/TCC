package com.udacity.shahd.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class ContainersList extends AppCompatActivity{
    ContainersAdapter mAdapter;
    EditText titleEditTxt;
    EditText descEditTxt;
    static int mIndex;
    public static ArrayList<Container> containers = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
//    private RecyclerView.LayoutManager mLayoutManager;
        Bundle args = new Bundle();
    private DatabaseHelper db;

//    @Override
//    public void onStart() {
//        super.onStart();
//        //RETRIEVE
//        containers =  new ArrayList<Container>();
//        Container c = new Container(1, "21.4149474","39.8997368", "mina",  "c-m-1");
//        containers.add(c);
//        //BIND
//        adapter=new ContainersAdapter(this,containers,mListener,mIndex);
//        mRecyclerView.setAdapter(adapter);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.containers_list);
        db = new DatabaseHelper(this);
        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            Intent intent = this.getIntent();
            if (intent != null) {
                mIndex = intent.getIntExtra("index", 0);
                Log.e("ContainersList", "index: " + mIndex);
            }
        }
        Log.d("ContainersList", "onCreate");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        MainActivity.createContainer();

//        containers.addAll(MainActivity.containersList);
        if(mIndex==1) {
            for (int i = 0; i < MainActivity.containersList.size(); i++) {
                if (MainActivity.containersList.get(i).getRegion().equals("mina"))
                    containers.add(MainActivity.containersList.get(i));
            }
        }
        else if(mIndex==2) {
            for (int i = 0; i < MainActivity.containersList.size(); i++) {
                if (MainActivity.containersList.get(i).getRegion().equals("arafat"))
                    containers.add(MainActivity.containersList.get(i));
            }
        }
        else if(mIndex==3) {
            for (int i = 0; i < MainActivity.containersList.size(); i++) {
                if (MainActivity.containersList.get(i).getRegion().equals("muzdalifah"))
                    containers.add(MainActivity.containersList.get(i));
            }
        }
////        //RETRIEVE
//        Container c1 = new Container(1, "39.895504","21.416422", "mina",  "c-m-1",80);
//        Container c2 = new Container(4, "39.901738","21.413705", "mina",  "c-m-2",40);
//        Container c3 = new Container(5, "39.891728","21.412058", "mina",  "c-m-3",10);
////        Container c4 = new Container(2, "21.4149474","39.8997368", "arafat",  "c-a-1",50);
////        Container c5 = new Container(3, "21.4149474","39.8997368", "muzdalifah",  "c-z-1",10);
//        containers.add(c1);
//        containers.add(c2);
//        containers.add(c3);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this); mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true); mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // specify an adapter (see also next example)
//        mAdapter = new ContainersAdapter(getApplicationContext(), containers, mListener, mIndex);

        mAdapter = new ContainersAdapter(this, containers,mIndex, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, Container container) {
                Log.d(String.valueOf(mIndex), "clicked position:" + container.getContainerName());
                Intent intent = new Intent();
                intent.setClass(v.getContext(), ContainersMap.class);

             intent.putExtra("container",  container);

             intent.putExtra("index", mIndex);
                startActivity(intent);

                // do what ever you want to do with it
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }
        public static ContainersList newInstance(int index) {
            ContainersList fragment = new ContainersList();
//            Bundle args = new Bundle();
//            args.putInt("index", index);
//            mIndex=index;
//            fragment.setArguments(args);
            return fragment;
        }


//    @Override
//    public void onListFragmentInteractionContainers(Container item, int index) {
//        Intent intent = new Intent();
//        intent.setClass(this, ContainersMap.class);
//
//        intent.putExtra("Tool",  item);
//
//        intent.putExtra("index", index);
//        startActivity(intent);
//    }



}
