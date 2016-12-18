package com.mscott.timer.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TimerConfigurationFile {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private File configurationFile;

    public TimerConfigurationFile(File configurationFile) {
        this.configurationFile = configurationFile;
    }

    public TimerConfiguration loadConfiguration() throws IOException {
        return OBJECT_MAPPER.readValue(configurationFile, TimerConfiguration.class);
    }

    public void saveConfiguration(TimerConfiguration timerConfiguration) throws IOException {
        OBJECT_MAPPER.writeValue(configurationFile, timerConfiguration);
    }
}
