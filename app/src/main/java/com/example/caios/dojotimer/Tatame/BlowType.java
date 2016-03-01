package com.example.caios.dojotimer.Tatame;

import com.example.caios.dojotimer.Tatame.Interface.IBlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caios on 2/14/16.
 */
public class BlowType {
    public static IBlow scrapePlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 2;
            }
        };
    }

    public static IBlow kneeInBellyPlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 2;
            }
        };
    }

    public static IBlow crossingGuardPlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 3;
            }
        };
    }

    public static IBlow tumblePlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 2;
            }
        };
    }

    public static IBlow footprintPlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 4;
            }
        };
    }

    public static IBlow mountedPlayer() {
        return new IBlow() {
            @Override
            public int attack() {
                return 4;
            }
        };
    }

    public static IBlow addPenalty() {
        return new IBlow() {
            @Override
            public int attack() {
                return 1;
            }
        };
    }

    public static IBlow addAdvantage() {
        return new IBlow() {
            @Override
            public int attack() {
                return 1;
            }
        };
    }
}


