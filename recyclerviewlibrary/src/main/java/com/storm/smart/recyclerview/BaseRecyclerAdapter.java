package com.storm.smart.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdzheng on 2015/12/28.
 */
public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter implements SizeCaculator.SizeCalculatorDelegate {
    private List<IRecyclerItem> mDatas;

//    private ArrayMap<String, Double> photoAspectRatios;

    private Context mContext;

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
    }

    public List<IRecyclerItem> getmDatas() {
        return mDatas;
    }

    public void setDatas(List<IRecyclerItem> datas, boolean isAddHeader) {
        this.mDatas = datas;
        notifyDataSetChanged();
        if (isAddHeader) {
            IRecyclerItem item = new IRecyclerItem();
            item.setViewType(RecyclerChildViewType.TYPE_HEADER);
            mDatas.add(0, item);
        }
    }

    @Override
    public int getChildViewType(int position) {
        if (position < getItemCount()) {
            IRecyclerItem info = mDatas.get(position);
            return info.getViewType();
        }
        return 0;
    }


    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return mDatas.get(position).getViewType();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RecyclerChildViewType.TYPE_HEADER:
                return onCreateHeaderViewHolder(parent);
            case RecyclerChildViewType.TYPE_GROUP:
                return onCreateGroupViewHolder(parent);
            case RecyclerChildViewType.TYPE_NORMAL:
            default:
                return onCreateNormalViewHolder(parent);
        }
    }

    protected abstract VH onCreateGroupViewHolder(ViewGroup parent);

    protected abstract VH onCreateNormalViewHolder(ViewGroup parent);

    protected abstract VH onCreateHeaderViewHolder(ViewGroup parent);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IRecyclerItem item = mDatas.get(position);
        switch (item.getViewType()) {
            case RecyclerChildViewType.TYPE_HEADER:
                onBindHeaderHolder((VH) holder, position);
                break;
            case RecyclerChildViewType.TYPE_GROUP:
                onBindGroupHolder((VH) holder, position);
                break;
            case RecyclerChildViewType.TYPE_NORMAL:
            default:
                onBindNormalHolder((VH) holder, position);
        }
    }


    protected abstract void onBindNormalHolder(VH holder, int position);

    protected abstract void onBindGroupHolder(VH holder, int position);

    protected abstract void onBindHeaderHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
