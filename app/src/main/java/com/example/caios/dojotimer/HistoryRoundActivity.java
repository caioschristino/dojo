package com.example.caios.dojotimer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.Tatame.Adapter.HistoryRecycleViewAdapter;
import com.example.caios.dojotimer.Tatame.Interface.IClickListener;

/**
 * Created by caios on 2/27/16.
 */
public class HistoryRoundActivity extends AppCompatActivity {
    private ListView listView;
    private History history;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        history = new History(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.listView = (ListView) findViewById(R.id.listview);
        HistoryRecycleViewAdapter adapter = new HistoryRecycleViewAdapter(history.getAllRouds(), new IClickListener() {
            @Override
            public void onClickItem(Round r) {
                Snackbar.make(listView, "Tap on: " + r.getTimeRound(), Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}