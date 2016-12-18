package com.mscott.timer.settings;

import java.util.List;
import java.util.Objects;

public class TimerConfiguration {

    private Integer turnLengthInMinutes;
    private List<String> names;

    public Integer getTurnLengthInMinutes() {
        return turnLengthInMinutes;
    }

    public void setTurnLengthInMinutes(Integer turnLengthInMinutes) {
        this.turnLengthInMinutes = turnLengthInMinutes;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerConfiguration that = (TimerConfiguration) o;
        return Objects.equals(turnLengthInMinutes, that.turnLengthInMinutes) &&
                Objects.equals(names, that.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnLengthInMinutes, names);
    }
}
