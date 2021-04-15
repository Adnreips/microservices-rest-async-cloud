package com.springboot.microservice.forex.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import com.springboot.microservice.forex.repositories.ExchangeValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * MessageListener
 *
 * @author "Andrei Prokofiev"
 */

@Component
@Slf4j
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




    @JmsListener(destination = "${my.jms.queue}")
    public void receiveMessage(final Message message) throws JMSException, JsonProcessingException {
        String messageData = null;
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            messageData = textMessage.getText();
        }

        log.info("Received message {}", messageData);
//        CurrencyConversionBean exchangeValue = new ObjectMapper().readValue(((TextMessage) message).getText(),
//                CurrencyConversionBean.class);
//        System.out.println(exchangeValue.getFrom() + " " + exchangeValue.getTo());
//
//        ExchangeValue exchangeValue1 = repository.findByFromAndTo(exchangeValue.getFrom(), exchangeValue.getTo());
//
//
//        exchangeValue.setPort(exchangeValue1.getPort());
        sender.sendMessageObject(queueName, messageData);
//        logger.info("Received message {}", jmsTemplate.getMessageConverter().fromMessage(message));

    }

    @JmsListener(destination = "${my.jms.queue.object}")
    public void receiveMessage2(CurrencyConversionBean message) throws JMSException, JsonProcessingException {
        log.info("Received message ", message);
//        TextMessage objectMessage = (TextMessage) message;
//        CurrencyConversionBean conversionBean = new ObjectMapper().readValue(((TextMessage) message).getText(),CurrencyConversionBean.class);
//        logger.info("Received message {}", conversionBean);


    }


}
