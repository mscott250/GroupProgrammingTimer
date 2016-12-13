package com.mscott.timer.stage;

import com.mscott.timer.controller.ChangeTurnWindowController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeTurnWindowStage extends FXMLSceneStage {

    @Autowired
    public ChangeTurnWindowStage(String uri, ChangeTurnWindowController controller) {
        super(uri, controller);
    }

    @Override
    public ChangeTurnWindowController getController() {
        return (ChangeTurnWindowController) super.getController();
    }

    @Override
    protected void initialiseUI() {
        super.initialiseUI();

        setFullScreen(true);
    }
}
