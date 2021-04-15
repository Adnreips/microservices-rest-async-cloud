package com.springboot.microservice.ccyconversion.controllers;

import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import com.springboot.microservice.ccyconversion.jms.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * CurrencyConversionControllerJms
 *
 * @author "Andrei Prokofiev"
 */

@RestController
@RequestMapping("/currency-converter")

public class CCControllerJms {
    @Autowired
    private Sender sender;

    @Value("${my.jms.queue}")
    private String queueName;

    @Value("${my.jms.queue.object}")
    private String queueNameObject;

    //Переделать mapping url
    @RequestMapping("/currency-converte")
    public CurrencyConversionBean convertCurrency(@RequestBody CurrencyConversionBean bean) {

        sender.sendMessageObject(queueName, bean);
//        sender.sendMessageObject(queueNameObject, bean);

        return bean;
    }

}
