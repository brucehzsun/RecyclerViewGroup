package com.storm.smart.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.storm.smart.recyclerview.SizeCaculator;

/**
 * Created by asdzheng on 2015/12/28.
 */
public class PhotosAdapter extends BaseRecyclerAdapter<PhotosAdapter.MyViewHolder> implements SizeCaculator.SizeCalculatorDelegate {
    private Context mContext;

    OnItemClickListener mListener;

    public PhotosAdapter(Context context, OnItemClickListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
    }


    @Override
    protected MyViewHolder onCreateGroupViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    protected MyViewHolder onCreateNormalViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    protected MyViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View header = LayoutInflater.from(mContext).inflate(R.layout.layout_header, parent, false);
        MyViewHolder headerHolder = new MyViewHolder(header);
        return headerHolder;
    }

    @Override
    protected void onBindNormalHolder(final MyViewHolder holder, final int position) {
        ImageItem item = (ImageItem) getmDatas().get(position);
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

    @Override
    protected void onBindGroupHolder(MyViewHolder holder, int position) {
        ImageItem item = (ImageItem) getmDatas().get(position);
        holder.title.setText(item.getTitle());
    }

    @Override
    protected void onBindHeaderHolder(MyViewHolder holder, int position) {

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
