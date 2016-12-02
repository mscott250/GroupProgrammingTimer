package com.mscott.timer.scheduling;

import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class TurnScheduler {

    private Timer turnTimer = new Timer();

    private AtomicBoolean timerRunning = new AtomicBoolean(false);

    private TurnOverListener turnOverListener;



    private long delayInMs;

    public void setTurnOverListener(TurnOverListener turnOverListener) {
        this.turnOverListener = turnOverListener;
    }

    public void setDelayInMs(long delayInMs) {
        this.delayInMs = delayInMs;
    }

    public void startTimer() {

        if (!timerRunning.get()) {

            timerRunning.set(true);

            turnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    turnOverListener.turnOver();
                }
            }, delayInMs);
        }
    }

    @PreDestroy
    public void stopTimer() {
        timerRunning.set(false);
        turnTimer.cancel();
        // TODO: there must be a better solution than using this
        turnTimer.purge();
    }
}
