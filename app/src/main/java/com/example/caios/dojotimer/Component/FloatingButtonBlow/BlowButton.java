package com.example.caios.dojotimer.Component.FloatingButtonBlow;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.caios.dojotimer.Models.Player;
import com.example.caios.dojotimer.Tatame.Interface.IBlow;

/**
 * Created by caios on 2/27/16.
 */
public class BlowButton {
    Context baseContext;
    String title;
    Drawable background;
    IBlow IBlow;
    Player p;

    public Context getBaseContext() {
        return baseContext;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getBackground() {
        return background;
    }

    public BlowButton(Context baseContext, String title, Drawable background, Player p,IBlow IBlow){
        this.baseContext = baseContext;
        this.title = title;
        this.background = background;
        this.p = p;
        this.IBlow = IBlow;
    }

    public void addPoint() {
        p.addAttack(IBlow);
    }
}
