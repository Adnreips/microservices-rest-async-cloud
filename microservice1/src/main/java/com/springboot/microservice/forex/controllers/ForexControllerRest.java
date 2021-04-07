package com.springboot.microservice.forex.controllers;

import com.springboot.microservice.forex.repositories.ExchangeValueRepository;
import com.springboot.microservice.forex.services.ExchangeValue;
import com.springboot.microservice.forex.services.ExchangeValueService;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ForexController
 *
 * @author "Andrei Prokofiev"
 */

@RestController
public class ForexControllerRest {

    private Logger logger = LoggerFactory.getLogger(ForexControllerRest.class);

    Integer count = 1;

    @Autowired
    private Environment environment;

//    @Autowired
    private ExchangeValueRepository repository;

//    @Autowired
    private ExchangeValueService exchangeValueService;

    public ForexControllerRest() {
    }


        @Autowired
    public ForexControllerRest(Environment environment,
                               ExchangeValueRepository repository,
                               ExchangeValueService exchangeValueService) {
        this.environment = environment;
        this.repository = repository;
        this.exchangeValueService = exchangeValueService;
    }

    @RequestMapping(value = "/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue =
                repository.findByFromAndTo(from, to);
        exchangeValue.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info(String.valueOf(count++));
        return exchangeValue;
    }

    @RequestMapping(value = "/currency-exchange-async/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValueAsync
            (@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = null;
        try {
            exchangeValue = exchangeValueService.getAsyncResult(from, to).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return exchangeValue;
    }
}