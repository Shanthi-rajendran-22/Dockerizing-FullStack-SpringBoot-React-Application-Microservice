package com.css.unitconvertor.controller;

import com.css.unitconvertor.model.Convertor;
import com.css.unitconvertor.repository.ConvertorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConvertorController {
    @Autowired
    private ConvertorRepository convertorRepository;


    @GetMapping("/convertor/from/{from}/to/{to}")
    public Convertor retrieveExchangeValue(@PathVariable String from, @PathVariable String to,
                                           @RequestHeader Map<String, String> headers) {

        Convertor value = convertorRepository.findByFromAndTo(from, to);

        if (value == null) {
            throw new RuntimeException("Unable to find data to convert " + from + " to " + to);
        }

        return value;
    }

}
