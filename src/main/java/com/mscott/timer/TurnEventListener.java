package com.mscott.timer;

public interface TurnEventListener {

    void startTurns(long turnLengthInMs);
    void stopTurns();
    void startNextTurn();
}
