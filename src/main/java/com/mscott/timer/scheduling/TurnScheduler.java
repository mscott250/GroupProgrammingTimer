package com.mscott.timer.scheduling;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class TurnScheduler {

    private Timer timer = new Timer();

    public void startTurn(Runnable turnEndedTask, long turnLengthInMs) {

        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnEndedTask.run();
            }
        }, turnLengthInMs);
    }

    @PreDestroy
    public void stopTurn() {

        if (timer != null) {
            timer.cancel();
            // cancel stops any new tasks being scheduled, so signal to create a new instance for any further tasks
            timer = null;
        }
    }
}
