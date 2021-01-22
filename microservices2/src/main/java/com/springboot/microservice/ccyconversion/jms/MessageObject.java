package com.springboot.microservice.ccyconversion.jms;

import java.io.Serializable;

/**
 * MessageObject
 *
 * @author "Andrei Prokofiev"
 */

public class MessageObject implements Serializable {

    private String name;

    public MessageObject() {
    }

    public MessageObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
