package com.css.unitconversion.controller;

import java.math.BigDecimal;

import com.css.unitconversion.pojo.ConvertedPOJO;
import com.css.unitconversion.serviceproxy.ConvertorServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitConversionController {

    @Autowired
	private ConvertorServiceProxy proxy;

	@GetMapping("/convertor/from/{from}/to/{to}/quantity/{quantity}")
	public ConvertedPOJO convertCurrency(@PathVariable String from, @PathVariable String to,
										 @PathVariable BigDecimal quantity) {

		ConvertedPOJO response = proxy.retrieveConversionValue(from, to);
		return new ConvertedPOJO(response.getId(), from, to, response.getValue(), quantity,
				quantity.multiply(response.getValue()));
	}
}
