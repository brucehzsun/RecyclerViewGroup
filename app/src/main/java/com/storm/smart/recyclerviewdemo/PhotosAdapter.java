package com.storm.smart.recyclerviewdemo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.storm.smart.recyclerview.RecyclerChildViewType;
import com.storm.smart.recyclerview.SizeCaculator;

import java.util.List;

/**
 * Created by asdzheng on 2015/12/28.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> implements SizeCaculator.SizeCalculatorDelegate {
    private List<ImageItem> mPhotos;

//    private ArrayMap<String, Double> photoAspectRatios;

    private Context mContext;

    OnItemClickListener mListener;

    public PhotosAdapter(Context context, List<ImageItem> mPhotos, OnItemClickListener listener) {
        this.mPhotos = mPhotos;
        mContext = context;
        mListener = listener;
    }

    public void setHearder() {
        ImageItem item = new ImageItem();
        item.setType(RecyclerChildViewType.TYPE_HEADER);
        mPhotos.add(0, item);
    }

    @Override
    public int getChildViewType(int position) {
        if (position < getItemCount()) {
            ImageItem info = mPhotos.get(position);
            return info.getType();
        }
        return 0;
    }


    public void clear() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return mPhotos.get(position).getType();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RecyclerChildViewType.TYPE_HEADER:
                View header = LayoutInflater.from(mContext).inflate(R.layout.layout_header, parent, false);
                MyViewHolder headerHolder = new MyViewHolder(header);
                return headerHolder;
            default:
                View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
                MyViewHolder viewHolder = new MyViewHolder(view);
                return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ImageItem item = mPhotos.get(position);
        switch (item.getType()) {
            case RecyclerChildViewType.TYPE_HEADER:
                break;
            default:
                holder.title.setText(item.getTitle());
//        holder.imageView.setImageURI(Uri.parse(item.getUrl()));
                if (mListener != null) {
                    holder.title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onItemClick(holder.title, position);
                        }
                    });

                    holder.title.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mListener.onItemLongClick(v, position);
                            return false;
                        }
                    });
                }
        }
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_recyclerview_text);
//            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    interface OnItemClickListener {
        void onItemClick(TextView v, int pos);

        void onItemLongClick(View v, int pos);
    }
}
