package com.mscott.timer.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

@Component
public class GroupList {

    private ObservableList<String> groupNames = FXCollections.observableArrayList();

    private int currentPersonIndex = -1; // initialise with -1, so we start with the first person in the list

    public ObservableList<String> getGroupNames() {
        return groupNames;
    }

    public void addPerson(String name) {
        if (!groupNames.contains(name)) {
            groupNames.add(name);
        }
    }

    public void removePerson(String name) {
        groupNames.remove(name);
    }

    public void clear() {
        groupNames.clear();
    }

    public boolean isEmpty() {
        return groupNames.isEmpty();
    }

    public String getNextPerson() {

        if (groupNames.isEmpty()) {
            return null;
        }

        if (currentPersonIndex >= (groupNames.size() - 1)) {
            // hit the end of the names, wrap to the beginning
            currentPersonIndex = 0;
        } else {
            currentPersonIndex++;
        }

        return groupNames.get(currentPersonIndex);
    }
}
