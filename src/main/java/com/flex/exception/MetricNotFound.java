package com.flex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jasonskipper on 5/9/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetricNotFound extends RuntimeException {
    public MetricNotFound(String msg){
        super(msg);
    }
}
