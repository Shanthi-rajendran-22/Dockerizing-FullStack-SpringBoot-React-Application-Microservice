package com.docker.restapimysql.helloworldmysql.pojo;

public class HelloWorldPojo {
    private String message;

    public HelloWorldPojo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
