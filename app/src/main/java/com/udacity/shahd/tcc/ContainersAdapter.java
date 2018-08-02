package com.udacity.shahd.tcc;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ContainersAdapter extends RecyclerView.Adapter<ContainerViewHolder> {

    Context c;
    CustomItemClickListener mListener;
    public  int mTag;
    public static ArrayList<Container> mContainers;
//    private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public ContainersAdapter(Context c, ArrayList<Container> containers, int tag, CustomItemClickListener listener) {
        this.c = c;
        this.mContainers = containers;
        this.mListener = listener;
        mTag = tag;
    }



    @NonNull
    @Override
    public ContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.container_item,parent,false);
//        v.setOnClickListener(mOnClickListener);
        return new ContainerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContainerViewHolder holder, int position) {
        Container item=mContainers.get(position);
        holder.bind(mContainers.get(position), mListener);
        holder.nameTxt.setText(item.getContainerName());
        holder.regionTxt.setText(item.getRegion());
        if(item.getStatus()<=25)
        holder.trashImage.setImageDrawable(c.getResources().getDrawable(R.drawable.green_trash));
        else if(item.getStatus()<60 && item.getStatus()>25)
        holder.trashImage.setImageDrawable(c.getResources().getDrawable(R.drawable.yellow_trash));
        else if(item.getStatus()>=60)
        holder.trashImage.setImageDrawable(c.getResources().getDrawable(R.drawable.red_trash));
//        holder.descTxt.setText(item.getDescription());
        holder.mItem=item;
        holder.mTag=mTag;


//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
////                    mListener.onListFragmentInteractionContainers(holder.mItem,holder.mTag);
//                    Intent intent = new Intent();
//                    intent.setClass(c, ContainersMap.class);
//
//                    intent.putExtra("Tool",  holder.mItem);
//                    String item = holder.mItem.getContainerName();
//                    Toast.makeText(v.getContext(),item, Toast.LENGTH_LONG).show();
//                    intent.putExtra("index", holder.mTag);
//                    c.startActivity(intent);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mContainers.size();
    }
}

