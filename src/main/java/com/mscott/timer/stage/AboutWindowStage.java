package com.mscott.timer.stage;

import com.mscott.timer.controller.AboutWindowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AboutWindowStage extends FXMLSceneStage {

    @Autowired
    public AboutWindowStage(String uri, AboutWindowController controller) {
        super(uri, controller);
    }

    @Override
    public AboutWindowController getController() {
        return (AboutWindowController) super.getController();
    }

    @Override
    protected void initialiseUI() {
        super.initialiseUI();

        setTitle("Timer - About");
        setResizable(false);
    }
}
