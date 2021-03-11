package com.mooc.moocServer.dto;

public class ErrorDto {
    public final String url;
    public final String message;

    public ErrorDto(String url, Exception exp){
        this.url = url;
        this.message = exp.getMessage();
    }
}
