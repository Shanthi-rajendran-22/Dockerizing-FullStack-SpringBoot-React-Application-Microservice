package com.docker.restapimysql.helloworldmysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class HelloWorldModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;

    public HelloWorldModel(){}
    public HelloWorldModel(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public HelloWorldModel(String msg) {
        this.message = msg;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
