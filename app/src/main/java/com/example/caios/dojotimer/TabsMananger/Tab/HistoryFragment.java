package com.example.caios.dojotimer.TabsMananger.Tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.caios.dojotimer.Models.Round;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.R;
import com.example.caios.dojotimer.TabsMananger.Tab.Adapter.HistoryTabAdapter;

import java.util.List;

/**
 * Created by caios on 2/21/16.
 */
public class HistoryFragment extends Fragment {
    private View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        context = inflater.getContext();


        return view;
    }
}