package com.springboot.microservice.ccyconversion.jms;

import java.util.UUID;

/**
 * MessageSample
 *
 * @author "Andrei Prokofiev"
 */
public class MessageSample implements MessageBrokerMessage{

    private UUID id = UUID.randomUUID();

    @Override
    public String toString() {
        return "MessageSample{" +
                "id=" + id +
                '}';
    }

}
