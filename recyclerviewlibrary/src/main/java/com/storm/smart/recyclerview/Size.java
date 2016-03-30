package com.storm.smart.recyclerview;

import java.io.Serializable;

/**
 * Created by asdzheng on 2015/12/26.
 */
public class Size implements Serializable{
    private int height;
    private int width;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}