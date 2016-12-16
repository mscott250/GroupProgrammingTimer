package com.mscott.timer.stage;

import com.mscott.timer.controller.ChangeTurnWindowController;

public class ChangeTurnWindowStage extends FXMLSceneStage {

    public ChangeTurnWindowStage(String uri, ChangeTurnWindowController controller) {
        super(uri, controller);
        initialiseUI();
    }

    @Override
    public ChangeTurnWindowController getController() {
        return (ChangeTurnWindowController) super.getController();
    }

    private void initialiseUI() {
        setFullScreen(true);
    }
}
