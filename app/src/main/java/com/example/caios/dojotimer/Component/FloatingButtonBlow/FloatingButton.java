package com.example.caios.dojotimer.Component.FloatingButtonBlow;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.example.caios.dojotimer.Component.FloatingButtonBlow.BlowButton;
import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Created by caios on 2/27/16.
 */
public class FloatingButton {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static FloatingActionButton getNewButton(final BlowButton b) {
        FloatingActionButton fab = new FloatingActionButton(b.getBaseContext());
        fab.setTitle(b.getTitle());
        fab.setBackground(b.getBackground());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.addPoint();
            }
        });
        return fab;
    }
}
