package com.example.caios.dojotimer.TabsMananger.Tab;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Persistence.Controller.History;
import com.example.caios.dojotimer.R;
import com.example.caios.dojotimer.Tatame.BlowType;
import com.example.caios.dojotimer.Tatame.RoundFight;
import com.example.caios.dojotimer.Tatame.Stopwatch;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by caios on 2/21/16.
 */
public class RoundFragment extends Fragment {
    private View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        context = inflater.getContext();

        return view;
    }
}