package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car already in use by another driver ...")
public class CarAlreadyInUseException extends Exception
{
    static final long serialVersionUID = 1L;

    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
