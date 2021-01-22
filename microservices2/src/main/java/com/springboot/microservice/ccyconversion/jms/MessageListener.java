package com.springboot.microservice.ccyconversion.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * MessageListener
 *
 * @author "Andrei Prokofiev"
 */

@Component
public class MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @JmsListener(destination = "${se.jms.queue}")
    public void receiveMessage(CurrencyConversionBean currencyConversionBean) throws JMSException,
            JsonProcessingException {
//
//        logger.info("Received message {}", ((TextMessage) message).getText());
//        CurrencyConversionBean exchangeValue = new ObjectMapper()
//                .readValue(((TextMessage) message).getText(),
//                CurrencyConversionBean.class);
//
        logger.info("Received message {}", currencyConversionBean);
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
