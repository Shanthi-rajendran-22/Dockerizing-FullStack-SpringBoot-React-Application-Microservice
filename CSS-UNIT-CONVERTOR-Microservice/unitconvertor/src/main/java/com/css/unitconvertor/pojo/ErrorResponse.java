package com.css.unitconvertor.pojo;

import java.util.List;

public class ErrorResponse {
    String timestamp;
    List<String> msg;
    String api;
    String statusCode;
    public ErrorResponse() {
    }

    public ErrorResponse(String timestamp,List<String> msg, String api, String statusCode) {
        this.timestamp = timestamp;
        this.msg = msg;
        this.api = api;
        this.statusCode = statusCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<String> getMsg() {
        return msg;
    }

    public String getApi() {
        return api;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
