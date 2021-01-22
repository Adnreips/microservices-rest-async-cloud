package com.springboot.microservice.forex.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import com.springboot.microservice.forex.repositories.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * MessageListener
 *
 * @author "Andrei Prokofiev"
 */
@Component
public class MessageListener {

    @Autowired
    private Sender sender;

    @Value("${se.jms.queue}")
    private String queueName;

    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    JmsTemplate jmsTemplate;

    private CurrencyConversionBean exchangeValue;

    private Logger logger = LoggerFactory.getLogger(MessageListener.class);


    @JmsListener(destination = "${my.jms.queue}")
    public void receiveMessage(CurrencyConversionBean message) throws JMSException, JsonProcessingException {

        logger.info("Received message {}", message);
//        CurrencyConversionBean exchangeValue = new ObjectMapper().readValue(((TextMessage) message).getText(),
//                CurrencyConversionBean.class);
//        System.out.println(exchangeValue.getFrom() + " " + exchangeValue.getTo());
//
//        ExchangeValue exchangeValue1 = repository.findByFromAndTo(exchangeValue.getFrom(), exchangeValue.getTo());
//
//
//        exchangeValue.setPort(exchangeValue1.getPort());
        sender.sendMessageObject(queueName, message);
//        logger.info("Received message {}", jmsTemplate.getMessageConverter().fromMessage(message));

    }

    @JmsListener(destination = "${my.jms.queue.object}")
    public void receiveMessage2(Message message) throws JMSException {
//        logger.info("Received message {}", message);

    }


}
