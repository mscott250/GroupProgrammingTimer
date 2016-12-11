package com.mscott.timer.config;

import com.mscott.timer.controller.AboutWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com.mscott.timer")
public class BeanConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    @Bean(name = "aboutWindowStage")
    public Stage aboutWindowStage(AboutWindowController aboutWindowController) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/aboutWindow.fxml"));
        fxmlLoader.setController(aboutWindowController);
        Parent root = fxmlLoader.load();

        Stage aboutWindowStage = new Stage();
        aboutWindowStage.setResizable(false);
        aboutWindowStage.setTitle("Timer - About");
        aboutWindowStage.setScene(new Scene(root));

        return aboutWindowStage;
    }
}
