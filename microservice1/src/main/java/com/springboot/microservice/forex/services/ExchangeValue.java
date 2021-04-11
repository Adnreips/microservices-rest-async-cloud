package com.springboot.microservice.forex.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ExchangeValue
 *
 * @author "Andrei Prokofiev"
 */

@Data
@AllArgsConstructor
@Service
@NoArgsConstructor
@Entity
public class ExchangeValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="currency_from")
    private String from;

    @Column(name="currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    @Transient
    private BigDecimal quantity;

    @Transient
    private BigDecimal totalCalculatedAmount;

    private int port;

}