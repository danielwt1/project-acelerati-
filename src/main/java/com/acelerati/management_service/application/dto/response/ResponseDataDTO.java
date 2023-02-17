package com.acelerati.management_service.application.dto.response;

public class ResponseDataDTO<T> {

    private Integer code;
    private T data;

    public ResponseDataDTO() {
    }

    public ResponseDataDTO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
