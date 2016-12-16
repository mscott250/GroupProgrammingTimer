package com.mscott.timer.config;

import com.mscott.timer.controller.AboutWindowController;
import com.mscott.timer.controller.ChangeTurnWindowController;
import com.mscott.timer.controller.MainWindowController;
import com.mscott.timer.stage.AboutWindowStage;
import com.mscott.timer.stage.ChangeTurnWindowStage;
import com.mscott.timer.stage.MainWindowStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com.mscott.timer")
public class BeanConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Autowired
    public MainWindowStage mainWindowStage(MainWindowController mainWindowController) {
        return new MainWindowStage("/view/mainWindow.fxml", mainWindowController);
    }

    @Bean
    @Autowired
    public ChangeTurnWindowStage changeTurnWindowStage(ChangeTurnWindowController changeTurnWindowController) {
        return new ChangeTurnWindowStage("/view/changeTurnWindow.fxml", changeTurnWindowController);
    }

    @Bean
    @Autowired
    public AboutWindowStage aboutWindowStage(AboutWindowController aboutWindowController) {
        return new AboutWindowStage("/view/aboutWindow.fxml", aboutWindowController);
    }
}
