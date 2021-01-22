package com.springboot.microservice.ccyconversion.controllers;

import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import com.springboot.microservice.ccyconversion.jms.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SenderResource
 *
 * @author "Andrei Prokofiev"
 */

@RestController
public class SenderController {

    @Autowired
    private Sender sender;

    @Value("${my.jms.queue}")
    private String queueName;

    @Value("${my.jms.queue.object}")
    private String queueNameObject;

    public SenderController(Sender sender) {
        this.sender = sender;
    }

    @PostMapping("/send")
    public String send(@RequestBody String messageSample){

        sender.sendMessage(queueName, messageSample);
        return "sended";
    }

    @PostMapping("/send2")
    public String sendObject(@RequestBody CurrencyConversionBean messageSample){

        sender.sendMessageObject(queueNameObject, messageSample);
        return "sended";

    }



}
