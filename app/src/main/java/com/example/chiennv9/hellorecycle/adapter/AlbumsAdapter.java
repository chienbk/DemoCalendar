package com.example.chiennv9.hellorecycle.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chiennv9.hellorecycle.R;
import com.example.chiennv9.hellorecycle.model.CalendarItem;
import com.example.chiennv9.hellorecycle.model.ItemNumber;

import java.util.Calendar;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
    private Context mContext;
    private List<ItemNumber> mAlbums;
    private OnItemClickListener mListener;
    private int mPosition = 0;
    private boolean isChoice = false;
    private Calendar calendar = Calendar.getInstance();

    public AlbumsAdapter(Context mContext, List<ItemNumber> mAlbums, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mAlbums = mAlbums;
        this.mListener = mListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ItemNumber album = mAlbums.get(position);

        if (album.isChoice()) {
            holder.tvNumber.setTypeface(Typeface.DEFAULT_BOLD);
            holder.llNumber.setAlpha(0.0f);
            holder.view.setBackgroundResource(R.drawable.item_bottom_border);
        } else {
            holder.tvNumber.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.llNumber.setAlpha(1.0f);
            holder.view.setBackgroundResource(R.drawable.item_bottom_border_un_select);
        }
        CalendarItem calendarItem = new CalendarItem(position, calendar);

        holder.tvNumber.setText(calendarItem.getDate() + " - " + calendarItem.getDay() + " - " + calendarItem.getMonth());

        holder.llNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPosition != position) {
                    mAlbums.get(mPosition).setChoice(false);
                    notifyItemChanged(mPosition);
                }
                if (!album.isChoice()) {
                    album.setChoice(true);
                    mAlbums.get(position).setChoice(true);
                } else {
                    album.setChoice(false);
                    mAlbums.get(position).setChoice(false);
                }
                mListener.onItemClickListener(v, position, album);
                mPosition = position;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNumber;
        public RelativeLayout llNumber;
        public View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            llNumber = itemView.findViewById(R.id.llNumber);
            view = itemView.findViewById(R.id.view_bottom);
        }
    }

    public void onNotificationItemChange(int position, ItemNumber itemNumber) {
        mAlbums.get(position).setChoice(itemNumber.isChoice());
        notifyItemChanged(position);
    }
}
