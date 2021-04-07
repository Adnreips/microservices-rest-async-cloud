package com.springboot.microservice.forex.services;

import com.springboot.microservice.forex.repositories.ExchangeValueRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@NoArgsConstructor
@Data
public class ExchangeValueService {

    private static Logger logger = LoggerFactory.getLogger(ExchangeValueService.class);

    Integer count = 1;

    private Environment environment;

    private ExchangeValueRepository repository;

    @Autowired
    public ExchangeValueService(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @Async
    public CompletableFuture<ExchangeValue> getAsyncResult(String from, String to) {

        ExchangeValue exchangeValue =
                repository.findByFromAndTo(from, to);
        exchangeValue.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("{}", exchangeValue);

        logger.info(String.valueOf(count++));
        return CompletableFuture.completedFuture(exchangeValue);
    }



}
