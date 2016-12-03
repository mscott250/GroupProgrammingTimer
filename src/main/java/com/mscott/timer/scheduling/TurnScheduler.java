package com.mscott.timer.scheduling;

import java.util.Timer;
import java.util.TimerTask;

public class TurnScheduler {

    private Timer timer = new Timer();

    public void startTurn(Runnable turnEndedTask, long turnLengthInMs) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnEndedTask.run();
            }
        }, turnLengthInMs);
    }

    public void stopTurn() {
        timer.cancel();
    }
}
