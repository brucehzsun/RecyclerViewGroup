package com.storm.smart.recyclerview;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by asdzheng on 2015/12/28.
 */
public class DisplayUtils {

    public class RecyclerViewType {
        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_GROUP = 1;
        public static final int TYPE_HEADER = 2;
        public static final int TYPE_FOODER = 3;
    }

    public static int dpToPx(final float n, final Context context) {
        return (int) TypedValue.applyDimension(1, n, context.getResources().getDisplayMetrics());
    }

    public static int pxToDp(final int n, final Context context) {
        return (int) TypedValue.applyDimension(0, (float) n, context.getResources().getDisplayMetrics());
    }

//    // 获取 设备的Hight
//    public static int getDeviceHeight(Context context) {
//        DisplayMetrics dm = new DisplayMetrics();
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        wm.getDefaultDisplay().getMetrics(dm);
//        return dm.heightPixels;
//    }
//
//    // 获取 设备的Width
//    public static int getDisplayWidth() {
//        DisplayMetrics dm = new DisplayMetrics();
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        wm.getDefaultDisplay().getMetrics(dm);
//        return dm.widthPixels;
//    }
}
