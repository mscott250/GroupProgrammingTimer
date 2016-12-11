package com.mscott.timer;

import javafx.application.HostServices;
import org.springframework.stereotype.Component;

@Component
public class HostServicesWrapper {

    private HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void showDocument(String uri) {
        hostServices.showDocument(uri);
    }
}
