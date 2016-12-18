package com.mscott.timer.settings;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class TimerConfigurationFileTest {

    @Test
    public void loadConfiguration() throws Exception {

        File configurationFile = new File(getClass().getResource("/settings/timer-configuration.json").getPath());

        TimerConfiguration expectedTimerConfiguration = new TimerConfiguration();
        expectedTimerConfiguration.setTurnLengthInMinutes(4);
        expectedTimerConfiguration.setNames(Arrays.asList("Fred", "Bob", "John"));

        TimerConfigurationFile timerConfigurationFile = new TimerConfigurationFile(configurationFile);
        TimerConfiguration timerConfiguration = timerConfigurationFile.loadConfiguration();

        Assertions.assertThat(timerConfiguration).isEqualTo(expectedTimerConfiguration);
    }

    @Test
    public void saveConfiguration() throws Exception {

        TimerConfiguration timerConfiguration = new TimerConfiguration();
        timerConfiguration.setTurnLengthInMinutes(4);
        timerConfiguration.setNames(Arrays.asList("Fred", "Bob", "John"));
        File configurationFile = File.createTempFile("timer-configuration", ".json");
        configurationFile.deleteOnExit();

        String expectedFileContents = "{\"turnLengthInMinutes\":4,\"names\":[\"Fred\",\"Bob\",\"John\"]}";

        TimerConfigurationFile timerConfigurationFile = new TimerConfigurationFile(configurationFile);
        timerConfigurationFile.saveConfiguration(timerConfiguration);

        try (BufferedReader in = new BufferedReader(new FileReader(configurationFile))) {
            String fileContents = in.readLine();
            Assertions.assertThat(fileContents).isEqualTo(expectedFileContents);
        }
    }
}
