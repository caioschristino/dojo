package com.example.caios.dojotimer.Component.FloatingButtonBlow;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;

import com.example.caios.dojotimer.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

/**
 * Created by caios on 2/27/16.
 */
public class FloatingMenu {
    private Context context;
    private FloatingActionsMenu floatingActionsMenu;

    public FloatingMenu(Context context, FloatingActionsMenu floatingActionsMenu) {
        this.context = context;
        this.floatingActionsMenu = floatingActionsMenu;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void addFabBlowMenuPlayer(AppCompatActivity activity, List<FloatingActionButton> buttons) {
        for (FloatingActionButton button : buttons) {
            this.floatingActionsMenu.addButton(button);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void addFabBlowMenuPlayer(AppCompatActivity activity, FloatingActionButton button) {
        this.floatingActionsMenu.addButton(button);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getImage(int id) {
        return context.getDrawable(id);
    }
}