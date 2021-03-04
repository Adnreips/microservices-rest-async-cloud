package com.springboot.microservice.ccyconversion.controllers;

import com.springboot.microservice.ccyconversion.services.CurrencyConversionBean;
import com.springboot.microservice.ccyconversion.services.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * CurrencyConversionController
 *
 * @author "Andrei Prokofiev"
 */



@RestController
public class CCControllerRest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

     @RequestMapping(value = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {
        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        logger.info("{}", response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}"/*, consumes = "application/json"*/)
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
                uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();
        logger.info("{}", response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }





    @GetMapping(value = "/currency-converter-async/from/{from}/to/{to}/quantity/{quantity}"/*, consumes = "application/json"*/)
    public CurrencyConversionBean convertCurrencyAsync(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) throws ExecutionException, InterruptedException {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        logger.info("Before request++++++++++++++");
//
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange-async/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);

        logger.info("After request++++++++++++++");

        CurrencyConversionBean currencyConversionBean = responseEntity.getBody();

        logger.info("{}", currencyConversionBean);
        return new CurrencyConversionBean(currencyConversionBean.getId(), from, to, currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()), currencyConversionBean.getPort());
    }

}
