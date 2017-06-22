package com.flex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception to tell user that a stat is not found and then return a 404 error
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetricNotFound extends RuntimeException {
    public MetricNotFound(String msg){
        super(msg);
    }
}
