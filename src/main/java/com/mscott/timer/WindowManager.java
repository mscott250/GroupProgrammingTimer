package com.mscott.timer;

import com.mscott.timer.scheduling.TurnScheduler;
import com.mscott.timer.stage.ChangeTurnWindowStage;
import com.mscott.timer.stage.MainWindowStage;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WindowManager implements TurnEventListener {

    private TurnScheduler turnScheduler;

    private MainWindowStage mainWindowStage;
    private ChangeTurnWindowStage changeTurnWindowStage;

    private long turnLengthInMs = -1;

    @Autowired
    public WindowManager(TurnScheduler turnScheduler,
                         MainWindowStage mainWindowStage,
                         ChangeTurnWindowStage changeTurnWindowStage) {

        this.turnScheduler = turnScheduler;
        this.mainWindowStage = mainWindowStage;
        this.changeTurnWindowStage = changeTurnWindowStage;

        mainWindowStage.getController().setTurnEventListener(this);
        changeTurnWindowStage.getController().setTurnEventListener(this);
    }

    public void showMainWindow() {
        mainWindowStage.show();
    }

    @Override
    public void startTurns(long turnLengthInMs) {

        this.turnLengthInMs = turnLengthInMs;

        hideMainWindow();
        // ask the first person to start their turn
        switchPerson();
    }

    @Override
    public void stopTurns() {
        turnScheduler.stopTurn();
    }

    @Override
    public void startNextTurn() {
        changeTurnWindowStage.close();
        turnScheduler.startTurn(this::switchPerson, turnLengthInMs);
    }

    private void hideMainWindow() {
        Platform.runLater(() -> mainWindowStage.setIconified(true));
    }

    private void switchPerson() {
        Platform.runLater(() -> {
            changeTurnWindowStage.getController().displayNextPerson();
            changeTurnWindowStage.show();
        });
    }
}
