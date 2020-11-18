package com.css.unitconversion.serviceproxy;

import com.css.unitconversion.pojo.ConvertedPOJO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "unit-convertor-service", url = "${CONVERTOR_URI:http://localhost:8000}")
public interface ConvertorServiceProxy {

	@GetMapping("/convertor/from/{from}/to/{to}")
	public ConvertedPOJO retrieveConversionValue(@PathVariable("from") String from,
											   @PathVariable("to") String to);
}