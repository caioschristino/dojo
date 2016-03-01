package com.example.caios.dojotimer.Tatame.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.R;
import com.example.caios.dojotimer.Tatame.Interface.IClickListener;

import java.util.List;

/**
 * Created by caios on 2/27/16.
 */
public class HistoryRecycleViewAdapter extends RecyclerView.Adapter<HistoryRecycleViewAdapter.ViewHolder> {
    private final List<Round> rounds;
    private final IClickListener delegate;

    public HistoryRecycleViewAdapter(List<Round> mItems, IClickListener mDelegate) {
        rounds = mItems;
        delegate = mDelegate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.r = rounds.get(position);
        holder.txNamePlayerB.setText(rounds.get(position).getLeftPlayer().getName());
        holder.txNamePlayerA.setText(rounds.get(position).getRightPlayer().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != delegate) {
                    delegate.onClickItem(holder.r);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rounds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txNamePlayerA;
        public final TextView txNamePlayerB;
        public Round r;

        public ViewHolder(View view) {
            super(view);
            txNamePlayerA = (TextView) view.findViewById(R.id.txNamePlayerA);
            txNamePlayerB = (TextView) view.findViewById(R.id.txNamePlayerB);
        }
    }
}