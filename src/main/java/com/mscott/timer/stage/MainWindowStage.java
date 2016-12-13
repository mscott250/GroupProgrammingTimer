package com.mscott.timer.stage;

import com.mscott.timer.controller.MainWindowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainWindowStage extends FXMLSceneStage {

    @Autowired
    public MainWindowStage(String uri, MainWindowController controller) {
        super(uri, controller);
    }

    @Override
    public MainWindowController getController() {
        return (MainWindowController) super.getController();
    }

    @Override
    protected void initialiseUI() {
        super.initialiseUI();

        setTitle("Timer");
    }
}
