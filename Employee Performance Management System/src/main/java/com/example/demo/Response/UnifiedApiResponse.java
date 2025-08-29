package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnifiedApiResponse {

    private ErrorResponse meta;
    private Object data;

    public UnifiedApiResponse() {}

    public UnifiedApiResponse(ErrorResponse meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    public UnifiedApiResponse meta(ErrorResponse meta) {
        this.meta = meta;
        return this;
    }

    public static UnifiedApiResponse success(Object data) {
        return new UnifiedApiResponse(null, data);
    }

    public static UnifiedApiResponse error(ErrorResponse error) {
        return new UnifiedApiResponse(error, null);
    }

    public ErrorResponse getMeta() {
        return meta;
    }

    public void setMeta(ErrorResponse meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
