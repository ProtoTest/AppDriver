package com.prototest.appdriver;

import java.awt.event.ActionEvent;

/**
 * Created by Brian on 9/9/2014.
 */
public class GolemEvent extends ActionEvent {
    public GolemEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
