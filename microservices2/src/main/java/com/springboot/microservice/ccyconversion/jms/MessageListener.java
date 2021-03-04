package com.springboot.microservice.ccyconversion.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * MessageListener
 *
 * @author "Andrei Prokofiev"
 */
@Slf4j
@Component
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = "${se.jms.queue}")
    public void receiveMessage(Message message) throws JMSException,
            JsonProcessingException {
        String messageData = null;
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            messageData = textMessage.getText();
        }

        log.info("Received message {}", messageData);
    }

//    @JmsListener(destination = "${my.jms.queue.object}")
//    public void receiveMessage2(CurrencyConversionBean message) {
//        logger.info("Received message {}", message);
//    }

//    @JmsListener(destination = "${my.jms.queue.map}")
//    public void receiveMessage3(Map message) {
//        logger.info("Received message {}", message);
//    }

}
