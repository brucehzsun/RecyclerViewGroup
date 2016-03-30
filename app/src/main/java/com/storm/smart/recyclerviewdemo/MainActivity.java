package com.storm.smart.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.storm.smart.recyclerview.SuitedItemDecoration;
import com.storm.smart.recyclerview.SuitedLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PhotosAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetItemTask task = new GetItemTask(new GetItemTask.OnGetInfoSuccessListener() {
            @Override
            public void onGetInfoSuccess(ArrayList<ImageItem> datas) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                PhotosAdapter adapter = new PhotosAdapter(MainActivity.this, datas, MainActivity.this);
                adapter.setHearder();
                recyclerView.setAdapter(adapter);
                SuitedLayoutManager layoutManager = new SuitedLayoutManager(getBaseContext(), adapter);
                layoutManager.setMaxRowHeight(getResources().getDisplayMetrics().heightPixels / 3);
                recyclerView.addItemDecoration(new SuitedItemDecoration(getBaseContext(), DisplayUtils.dpToPx(4.0f, MainActivity.this)));
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
        });
        task.execute("");


    }

    @Override
    public void onItemClick(TextView v, int pos) {

    }

    @Override
    public void onItemLongClick(View v, int pos) {

    }
}
