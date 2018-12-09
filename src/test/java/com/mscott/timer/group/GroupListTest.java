package com.mscott.timer.group;

import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GroupListTest {

    private GroupList groupList;

    @BeforeEach
    public void setup() {
        this.groupList = new GroupList();
    }

    @Test
    public void addNewPerson() {

        groupList.addNewPerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void addNewPerson_withDuplicateName_addsNoPerson() {

        groupList.addNewPerson("Fred");
        groupList.addNewPerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void addAllPersons() {

        groupList.addAllPersons(Arrays.asList("Fred", "Bob", "Tim"));

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(3);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
        Assertions.assertThat(groupNames.get(1)).isEqualTo("Bob");
        Assertions.assertThat(groupNames.get(2)).isEqualTo("Tim");
    }

    @Test
    public void removePerson() {

        groupList.addNewPerson("Fred");

        groupList.removePerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(0);
    }

    @Test
    public void removePerson_withUnknownName_removesNoPeople() {

        groupList.addNewPerson("Fred");

        groupList.removePerson("Bob");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void clear() {

        groupList.addNewPerson("Fred");

        groupList.clear();

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(0);
    }

    @Test
    public void isEmpty_withNoPeople_returnsTrue() {

        boolean isEmpty = groupList.isEmpty();

        Assertions.assertThat(isEmpty).isTrue();
    }

    @Test
    public void isEmpty_withPeople_returnsFalse() {

        groupList.addNewPerson("Fred");

        boolean isEmpty = groupList.isEmpty();

        Assertions.assertThat(isEmpty).isFalse();
    }

    @Test
    public void getNextPerson_withNoPeople_returnsNull() {

        String nextPersonName = groupList.getNextPerson();

        Assertions.assertThat(nextPersonName).isNull();
    }

    @Test
    public void getNextPerson_withPeople_returnsFirstNameOnFirstCall() {

        groupList.addNewPerson("Fred");
        groupList.addNewPerson("Bob");

        String nextPersonName = groupList.getNextPerson();

        Assertions.assertThat(nextPersonName).isEqualTo("Fred");
    }

    @Test
    public void getNextPerson_withPeople_returnsFirstNameOnReachingEndOfNames() {

        groupList.addNewPerson("Fred");
        groupList.addNewPerson("Bob");

        groupList.getNextPerson(); // Fred
        groupList.getNextPerson(); // Bob
        String nextPersonName = groupList.getNextPerson(); // Fred, again

        Assertions.assertThat(nextPersonName).isEqualTo("Fred");
    }

    @Test
    public void getNextPerson_withPeople_returnsNextPersonOnNextCall() {

        groupList.addNewPerson("Fred");
        groupList.addNewPerson("Bob");

        groupList.getNextPerson(); // Fred
        String nextPersonName = groupList.getNextPerson();

        Assertions.assertThat(nextPersonName).isEqualTo("Bob");
    }
}
