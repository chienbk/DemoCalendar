package com.example.chiennv9.hellorecycle;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chiennv9.hellorecycle.adapter.AlbumsAdapter;
import com.example.chiennv9.hellorecycle.adapter.OnItemClickListener;
import com.example.chiennv9.hellorecycle.model.ItemNumber;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private AlbumsAdapter mAlbumsAdapter;
    private List<ItemNumber> mNumbers;
    private RelativeLayout llSelectNumber;
    private LinearLayout llDelete;
    private TextView tvNumber;
    private ItemNumber itemNumber;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvListNumber);
        llSelectNumber = findViewById(R.id.llNumber);
        llDelete = findViewById(R.id.llDelete);
        tvNumber = findViewById(R.id.tvNumber);
        tvNumber.setTypeface(Typeface.DEFAULT_BOLD);

        llDelete.setOnClickListener(this);

        mNumbers = new ArrayList<>();
        mAlbumsAdapter = new AlbumsAdapter(this, mNumbers, this);

        // set a GridLayoutManager with 3 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAlbumsAdapter);

        prepareList();
    }

    private void prepareList() {

        for (int i = 0; i < 100; i++) {
            ItemNumber itemNumber = new ItemNumber("012242225" + i, false);
            mNumbers.add(itemNumber);
        }
        mAlbumsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(View view, int position, ItemNumber album) {
        this.itemNumber = album;
        this.mPosition = position;

        if (album.isChoice()) {
            llSelectNumber.setVisibility(View.VISIBLE);
            tvNumber.setText(album.getNumber());
        } else {
            llSelectNumber.setVisibility(View.GONE);
        }
        mAlbumsAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClick(View v) {
        llSelectNumber.setVisibility(View.GONE);
        //TODO update view
        itemNumber.setChoice(false);
        mAlbumsAdapter.onNotificationItemChange(mPosition, itemNumber);
    }
}
