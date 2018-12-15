# Group Programming Timer

A GUI application intended to act as a timer when carrying out group programming, which provides very direct and
obvious notifications when the timer is up and the developer's turn is over. The idea for this is that it helps ensure
that everyone in the group gets their turn typing and that developer's do not overrun their turn.

This has been created because the standard timers on OS X did not provide visible enough feedback when the timer has
ended, and to provide the opportunity to try some Java GUI development.

The application is written in Java 8 and uses JavaFX for the GUI framework.

## System Requirements

A Java 8 or newer JRE is the only requirement to run the application, all dependencies are included in the JAR, to
allow it to be run as a standalone application.

### OpenJDK 8 (Linux) Users

There is currently an issue with OpenJDK 8 relating to their implementation of the JavaFX `HostServices` class, whereby
using it can cause a class not found exception. This is used in this project for the about window, to launch the 
browser at the project homepage. Until this issue is fixed, users should use the Oracle JRE to run the application to 
avoid this problem. 

More details on the bug can be found [here](https://bugs.openjdk.java.net/browse/JDK-8160464).
