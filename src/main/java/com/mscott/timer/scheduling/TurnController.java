package com.mscott.timer.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Michael.Scott on 01/12/2016.
 */
public class TurnController {

    private Timer timer = new Timer();

    private long turnLengthInMs = 0;

    private AtomicBoolean timerStarted = new AtomicBoolean(false);

    private List<TurnEventListener> turnEventListeners = new ArrayList<>();

    public void addTurnEventListener(TurnEventListener turnEventListener) {
        this.turnEventListeners.add(turnEventListener);
    }

    public void startTurn() {

        if (!timerStarted.get()) {

            timerStarted.set(true);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (TurnEventListener turnEventListener : turnEventListeners) {
                        turnEventListener.turnEnded();
                    }
                }
            }, turnLengthInMs);
        }
    }

    public void stopTurn() {

        timer.cancel();

        timerStarted.set(false);

        for (TurnEventListener turnEventListener : turnEventListeners) {
            turnEventListener.
        }
    }
}
