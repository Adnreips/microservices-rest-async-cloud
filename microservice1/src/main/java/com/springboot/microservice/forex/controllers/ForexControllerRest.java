package com.springboot.microservice.forex.controllers;

import com.springboot.microservice.forex.jms.MessageListener;
import com.springboot.microservice.forex.services.ExchangeValue;
import com.springboot.microservice.forex.repositories.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jms.Message;
import java.awt.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * ForexController
 *
 * @author "Andrei Prokofiev"
 */

@RestController
public class ForexControllerRest {

    private Logger logger = LoggerFactory.getLogger(ForexControllerRest.class);

    Integer count = 1;
//    @Autowired
    private Environment environment;

//    @Autowired
    private ExchangeValueRepository repository;

    @Autowired
    public ForexControllerRest(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    public ForexControllerRest() {
    }

    @RequestMapping(value = "/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){


        ExchangeValue exchangeValue =
                repository.findByFromAndTo(from, to);

        exchangeValue.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info(String.valueOf(count++));
        return exchangeValue;
    }
}