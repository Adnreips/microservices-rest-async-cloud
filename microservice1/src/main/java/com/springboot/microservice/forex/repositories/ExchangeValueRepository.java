package com.springboot.microservice.forex.repositories;

import com.springboot.microservice.forex.services.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * ExchangeValueRepository
 *
 * @author "Andrei Prokofiev"
 */

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
