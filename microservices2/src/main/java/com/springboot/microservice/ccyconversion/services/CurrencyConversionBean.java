package com.springboot.microservice.ccyconversion.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CurrencyConversionBean
 *
 * @author "Andrei Prokofiev"
 */

@Data
@AllArgsConstructor
@Service
@NoArgsConstructor
@Component
//Добавить сщности request, response
public class CurrencyConversionBean implements Serializable {

    static final long SerialVersionUID = -1;
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal multiply;
    private int port;

}
