package com.mscott.timer.scheduling;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class TurnScheduler {

    private Timer turnTimer = new Timer();

    private AtomicBoolean timerRunning = new AtomicBoolean(false);

    private TurnOverListener turnOverListener;

    public TurnScheduler(TurnOverListener turnOverListener) {
        this.turnOverListener = turnOverListener;
    }

    public void startTimer(long delayInMs) {
        timerRunning.set(true);
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (timerRunning.get()) {
                    turnOverListener.turnOver();
                }
            }
        }, delayInMs);
    }

    public void stopTimer() {
        timerRunning.set(false);
        turnTimer.cancel();
        // TODO: there must be a better solution than using this
        turnTimer.purge();
    }
}
