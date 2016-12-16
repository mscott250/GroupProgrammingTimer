package com.mscott.timer.stage;

import com.mscott.timer.controller.AboutWindowController;

public class AboutWindowStage extends FXMLSceneStage {

    public AboutWindowStage(String uri, AboutWindowController controller) {
        super(uri, controller);
        initialiseUI();
    }

    @Override
    public AboutWindowController getController() {
        return (AboutWindowController) super.getController();
    }

    private void initialiseUI() {
        setTitle("Timer - About");
        setResizable(false);
    }
}
