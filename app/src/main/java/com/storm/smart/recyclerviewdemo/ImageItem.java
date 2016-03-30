package com.storm.smart.recyclerviewdemo;

import com.storm.smart.recyclerview.IRecyclerItem;

/**
 * Created by sunhongzhi on 2016/3/30.
 */
public class ImageItem extends IRecyclerItem {

    private String title;

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
