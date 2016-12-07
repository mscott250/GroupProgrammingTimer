package com.mscott.timer.scheduling;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

// TODO: try and remove these sleeps

public class TurnSchedulerTest {

    private TurnScheduler turnScheduler;

    @Before
    public void setup() {
        this.turnScheduler = new TurnScheduler();
    }

    @Test
    public void startTurn() throws Exception {

        Runnable turnEndedTask = Mockito.mock(Runnable.class);

        turnScheduler.startTurn(turnEndedTask, 0);

        // leave enough time for the task to be run
        Thread.sleep(10);

        Mockito.verify(turnEndedTask).run();
    }

    @Test
    public void stopTurn() throws Exception {

        Runnable turnEndedTask = Mockito.mock(Runnable.class);

        turnScheduler.startTurn(turnEndedTask, 100);

        turnScheduler.stopTurn();

        Thread.sleep(200);

        Mockito.verifyZeroInteractions(turnEndedTask);
    }

    @Test
    public void startTurn_afterTurnCancelled_startsTurn() throws Exception {

        Runnable firstTurnEndedTask = Mockito.mock(Runnable.class);
        Runnable secondTurnEndedTask = Mockito.mock(Runnable.class);

        turnScheduler.startTurn(firstTurnEndedTask, 100);

        turnScheduler.stopTurn();

        turnScheduler.startTurn(secondTurnEndedTask, 0);

        Thread.sleep(10);

        Mockito.verify(secondTurnEndedTask).run();
    }
}
