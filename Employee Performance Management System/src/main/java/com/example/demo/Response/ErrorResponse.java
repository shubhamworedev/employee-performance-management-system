package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String error;
    private Integer responseCode;
    private Object httpResponse;

    public ErrorResponse() {}

    public ErrorResponse(String error, Integer responseCode, Object httpResponse) {
        this.error = error;
        this.responseCode = responseCode;
        this.httpResponse = httpResponse;
    }

    public ErrorResponse error(String error) {
        this.error = error;
        return this;
    }

    public ErrorResponse responseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public ErrorResponse httpResponse(Object httpResponse) {
        this.httpResponse = httpResponse;
        return this;
    }

    public static ErrorResponse of(String error, Integer responseCode, Object httpResponse) {
        return new ErrorResponse(error, responseCode, httpResponse);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Object getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(Object httpResponse) {
        this.httpResponse = httpResponse;
    }
}


