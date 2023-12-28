package com.aust.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAccessException extends Exception{
    private static final long serialVersionUID = 1L;
    public NoAccessException(String message){
        super(message);
    }
}