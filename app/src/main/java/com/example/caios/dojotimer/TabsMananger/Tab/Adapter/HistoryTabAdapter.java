package com.example.caios.dojotimer.TabsMananger.Tab.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.R;

import java.util.List;

/**
 * Created by caios on 2/s21/16.
 */
public class HistoryTabAdapter extends ArrayAdapter<Round> {
    private List<Round> rounds;
    private Context context;

    public HistoryTabAdapter(Context context, int textViewResourceId, List<Round> rounds) {
        super(context, textViewResourceId, rounds);
        this.rounds = rounds;
        this.context = context;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_history_adapter, parent, false);

        Round r = getItem(position);
        if (r != null) {
            TextView txNamePlayerA = (TextView) rowView.findViewById(R.id.txNamePlayerA);
            txNamePlayerA.setText(r.getRightPlayer().getName());
            TextView txNamePlayerB = (TextView) rowView.findViewById(R.id.txNamePlayerB);
            txNamePlayerB.setText(r.getLeftPlayer().getName());
        }
        return rowView;
    }
}
