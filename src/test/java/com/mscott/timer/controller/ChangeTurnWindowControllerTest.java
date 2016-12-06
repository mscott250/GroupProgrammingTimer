package com.mscott.timer.controller;

import com.mscott.timer.group.GroupList;
import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTurnWindowControllerTest {

    private ChangeTurnWindowController changeTurnWindowController;

    @Mock
    private GroupList groupList;

    @Mock
    private Label nextPersonLabel;

    @Before
    public void setup() {
        this.changeTurnWindowController = new ChangeTurnWindowController(groupList);
        this.changeTurnWindowController.nextPersonLabel = nextPersonLabel;
    }

    @Test
    public void displayNextPerson_setsLabelValue() {

        Mockito.when(groupList.getNextPerson()).thenReturn("fred");

        changeTurnWindowController.displayNextPerson();

        Mockito.verify(nextPersonLabel).setText("fred please sit at the keyboard");
    }
}
