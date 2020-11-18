package com.css.unitconvertor.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Convertor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "unit_from")
    private String from;

    @Column(name = "unit_to")
    private String to;

    private BigDecimal value;

    public Convertor() {
    }

    public Convertor(Long id, String from, String to, BigDecimal value) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
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
}
