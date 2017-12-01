package com.example.admin.flickr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    public static List<Item> itemList = new ArrayList<>();
    Context context;


    public List<Item> getitemList() {
        return itemList;
    }



    public static final String TAG = "itemsRecycler";

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pics_recycler_item, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }
    public RecyclerAdapter(List<Item> itemList) {
        this.itemList = itemList;
}

    public RecyclerAdapter()
    {

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Item c  = itemList.get(position);
        if (c != null)
        {
            holder.tvTitle.setText(c.getTitle());
            holder.tvAuthorId.setText(c.getAuthorId());
            holder.tvTag.setText(c.getTags());
             Glide.with(context).load(c.getMedia().getM()).into(holder.image);

        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvTitle, tvAuthorId,tvTag;
        private final ImageView image;




        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthorId = itemView.findViewById(R.id.tvAuthorId);
            tvTag = itemView.findViewById(R.id.tvTag);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ImageActivity.class);
            intent.putExtra("imageUrl", itemList.get(getPosition()).getMedia().getM());
            view.getContext().startActivity(intent);

        }
    }
}
