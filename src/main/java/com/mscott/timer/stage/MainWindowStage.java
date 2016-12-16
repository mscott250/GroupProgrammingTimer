package com.mscott.timer.stage;

import com.mscott.timer.controller.MainWindowController;

public class MainWindowStage extends FXMLSceneStage {

    public MainWindowStage(String uri, MainWindowController controller) {
        super(uri, controller);
        initialiseUI();
    }

    @Override
    public MainWindowController getController() {
        return (MainWindowController) super.getController();
    }

    private void initialiseUI() {
        setTitle("Timer");
    }
}
