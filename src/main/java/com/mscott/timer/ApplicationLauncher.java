package com.mscott.timer;

/**
 * This launcher class is needed to allow the app to startup successfully on JavaFX 10+, trying to launch the app from
 * the MainApplication class instead causes a startup error about missing runtime components.
 *
 * @see <a href="http://mail.openjdk.java.net/pipermail/openjfx-dev/2018-June/021977.html">OpenJDK Thread</a>
 */
public class ApplicationLauncher {

    public static void main(String[] args) {
        // this launcher has to call the main method of the MainApplication class, it cannot call the Application
        // launch method directly, otherwise startup will fail because this launcher is not a subclass of Application
        MainApplication.main(args);
    }
}
