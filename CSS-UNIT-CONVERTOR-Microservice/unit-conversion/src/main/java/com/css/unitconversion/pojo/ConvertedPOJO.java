package com.css.unitconversion.pojo;

import javax.persistence.Column;
import java.math.BigDecimal;

public class ConvertedPOJO {
	private Long id;

	private String from;

	private String to;

	private BigDecimal value;

	private BigDecimal quantity;

	private BigDecimal calculated;

	public ConvertedPOJO() {}

	public ConvertedPOJO(Long id, String from, String to, BigDecimal value, BigDecimal quantity, BigDecimal calculated) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.value = value;
		this.quantity = quantity;
		this.calculated = calculated;
	}

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getValue() {
		return value;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getCalculated() {
		return calculated;
	}
}
