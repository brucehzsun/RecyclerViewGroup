package com.storm.smart.recyclerviewdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

/**
 * Created by sunhongzhi on 2016/3/30.
 */
public class GetItemTask extends AsyncTask<String, Void, ArrayList<ImageItem>> {

    OnGetInfoSuccessListener listener;

    public GetItemTask(OnGetInfoSuccessListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<ImageItem> doInBackground(String... params) {

        String url = "http://search.shouji.baofeng.com/column.php?id=1613%2C1618%2C3774%2C3799%2C2847%2C3798%2C1614%2C3513%2C3775%2C3590%2C1615%2C&platf=android&mtype=normal&g=23&ver=6.0.07&td=13&s=5825710736B3DE281FB3D3CF4D1EAC83EB29E2C6";
        String result = getJsonStringFrUrlNoParams(url, false, "");
        return parseGroupItems(result);
    }

    @Override
    protected void onPostExecute(ArrayList<ImageItem> imageItems) {
        super.onPostExecute(imageItems);
        if (listener != null) {
            listener.onGetInfoSuccess(imageItems);
        }
    }

    private ArrayList<ImageItem> parseGroupItems(String jsonString) {
        ArrayList<ImageItem> itemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject groupObj = resultArray.getJSONObject(i);
                ImageItem groupItem = new ImageItem();
                groupItem.setTitle(groupObj.getString("title"));
                groupItem.setType(1);
                itemList.add(groupItem);

                JSONArray detailArray = groupObj.getJSONArray("result");
                for (int j = 0; j < detailArray.length(); j++) {
                    JSONObject itemObj = detailArray.getJSONObject(j);
                    ImageItem item = new ImageItem();
                    item.setUrl(itemObj.getString("cover_url"));
                    item.setTitle(itemObj.getString("title"));
                    itemList.add(item);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }


    public static String getJsonStringFrUrlNoParams(String uri, boolean addSalt, String ua) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = null;
        InputStream inStream = null;


        HttpURLConnection conn = null;
        try {
            builder = new StringBuilder();
            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            if (!TextUtils.isEmpty(ua)) {
                conn.setRequestProperty("User-Agent", ua);
            }
            conn.setRequestMethod("GET");

            inStream = conn.getInputStream();
            byte[] buffer = new byte[1024 * 8];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                inStream = null;
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return builder.toString();
    }

    interface OnGetInfoSuccessListener {
        void onGetInfoSuccess(ArrayList<ImageItem> imageItems);
    }
}
