package com.mscott.timer.config;

import com.mscott.timer.WindowManager;
import com.mscott.timer.controller.ChangeTurnWindowController;
import com.mscott.timer.controller.MainWindowController;
import com.mscott.timer.group.GroupList;
import com.mscott.timer.scheduling.TurnScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public WindowManager windowManager() {
        return new WindowManager(turnScheduler(), mainWindowController(), changeTurnWindowController());
    }

    @Bean
    public MainWindowController mainWindowController() {
        return new MainWindowController(groupList());
    }

    @Bean
    public ChangeTurnWindowController changeTurnWindowController() {
        return new ChangeTurnWindowController(groupList());
    }

    @Bean
    public TurnScheduler turnScheduler() {
        return new TurnScheduler();
    }

    @Bean
    public GroupList groupList() {
        return new GroupList();
    }
}
