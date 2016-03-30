package com.storm.smart.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.storm.smart.recyclerview.SizeCaculator;

import java.util.List;

/**
 * Created by asdzheng on 2015/12/28.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> implements SizeCaculator.SizeCalculatorDelegate {
    private List<String> mPhotos;

//    private ArrayMap<String, Double> photoAspectRatios;

    private Context mContext;

    OnItemClickListener mListener;

    public PhotosAdapter(Context context, List<String> mPhotos, OnItemClickListener listener) {
        this.mPhotos = mPhotos;
        mContext = context;
        mListener = listener;
    }

    @Override
    public int getChildViewType(int position) {
        if (position < getItemCount()) {
            String info = mPhotos.get(position);
            if (position == 0 || position == 5 || position == 10) {
                return 1;
            } else {
//                double ratio = SuitUrlUtil.getAspectRadioFromUrl(info.photo);
//                return ratio;
                return 0;
            }
        }
        return 0;
    }

    public void bind(@NonNull List<String> mPhotos) {
        this.mPhotos.addAll(mPhotos);
        notifyDataSetChanged();
    }

    public void clear() {
        mPhotos.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText("" + position);
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
    public int getItemCount() {
        return mPhotos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) itemView.findViewById(R.id.item_recyclerview_text);
        }
    }

    interface OnItemClickListener {
        void onItemClick(TextView v, int pos);

        void onItemLongClick(View v, int pos);
    }
}
