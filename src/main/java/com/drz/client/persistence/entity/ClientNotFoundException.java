package com.drz.client.persistence.entity;

public class ClientNotFoundException extends Exception {

    private Integer code = 404;

    private String message = "Client not found";

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message, Integer code, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
