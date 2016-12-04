package com.mscott.timer.group;

import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class GroupListTest {

    private GroupList groupList;

    @Before
    public void setup() {
        this.groupList = new GroupList();
    }

    @Test
    public void addPerson() {

        groupList.addPerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void addPerson_withDuplicateName_addsNoPerson() {

        groupList.addPerson("Fred");
        groupList.addPerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void removePerson() {

        groupList.addPerson("Fred");

        groupList.removePerson("Fred");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(0);
    }

    @Test
    public void removePerson_withUnknownName_removesNoPeople() {

        groupList.addPerson("Fred");

        groupList.removePerson("Bob");

        ObservableList<String> groupNames = groupList.getGroupNames();

        Assertions.assertThat(groupNames).hasSize(1);
        Assertions.assertThat(groupNames.get(0)).isEqualTo("Fred");
    }

    @Test
    public void clear() {

        groupList.addPerson("Fred");

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

        groupList.addPerson("Fred");

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

        groupList.addPerson("Fred");
        groupList.addPerson("Bob");

        String nextPersonName = groupList.getNextPerson();

        Assertions.assertThat(nextPersonName).isEqualTo("Fred");
    }

    @Test
    public void getNextPerson_withPeople_returnsFirstNameOnReachingEndOfNames() {

        groupList.addPerson("Fred");
        groupList.addPerson("Bob");

        groupList.getNextPerson(); // Fred
        groupList.getNextPerson(); // Bob
        String nextPersonName = groupList.getNextPerson(); // Fred, again

        Assertions.assertThat(nextPersonName).isEqualTo("Fred");
    }

    @Test
    public void getNextPerson_withPeople_returnsNextPersonOnNextCall() {

        groupList.addPerson("Fred");
        groupList.addPerson("Bob");

        groupList.getNextPerson(); // Fred
        String nextPersonName = groupList.getNextPerson();

        Assertions.assertThat(nextPersonName).isEqualTo("Bob");
    }
}
