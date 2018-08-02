package com.udacity.shahd.tcc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContainerViewHolder extends RecyclerView.ViewHolder
{


    TextView nameTxt;
    TextView regionTxt;
    //    TextView descTxt;
    ImageView trashImage;
    public View mView;
    public Container mItem;
    int mTag;


    public ContainerViewHolder(View itemView) {
        super(itemView);

        mView=itemView;
        nameTxt= (TextView) itemView.findViewById(R.id.name);
        regionTxt= (TextView) itemView.findViewById(R.id.region);
//        descTxt= (TextView) itemView.findViewById(R.id.descEditText);
        trashImage=(ImageView)itemView.findViewById(R.id.tool_image);

    }

    public void bind(final Container item, final CustomItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(mView,item);
            }
        });
    }

//    @Override
//    public void onClick(View view) {
//        int selectedPosition = getAdapterPosition();
//        if( selectedPosition != RecyclerView.NO_POSITION )
//        {
//            Container item = ContainersAdapter.mContainers.get(selectedPosition);
////            ContainersAdapter.mListener.onListFragmentInteractionContainers(item,mTag);
//            Intent intent = new Intent();
//            intent.setClass(mView.getContext(), ContainersMap.class);
//
//            intent.putExtra("Tool",  mItem);
//
//            intent.putExtra("index", mTag);
//            mView.getContext().startActivity(intent);
//        }
//    }
}