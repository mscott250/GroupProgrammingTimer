package com.mscott.timer.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.RandomUtils;

public class GroupList {

    private ObservableList<String> groupNames = FXCollections.observableArrayList();

    private int currentPersonIndex;

    public ObservableList<String> getGroupNames() {
        return groupNames;
    }

    public void addPerson(String name) {
        if (!groupNames.contains(name)) {
            groupNames.add(name);
        }
    }

    public boolean isEmpty() {
        return groupNames.isEmpty();
    }

    public String getNextPerson() {
        currentPersonIndex = RandomUtils.nextInt(0, groupNames.size());
        return groupNames.get(currentPersonIndex);
    }
}
