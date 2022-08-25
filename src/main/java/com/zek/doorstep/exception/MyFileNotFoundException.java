package com.zek.doorstep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author EK
 *
 */
@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE, reason = "File Access not aval")
public class MyFileNotFoundException extends RuntimeException {
	
	 private static final long serialVersionUID = 1L;
//
//    public MyFileNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }
    
    public MyFileNotFoundException() {
        super();
    }
    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyFileNotFoundException(String message) {
        super(message);
    }
    public MyFileNotFoundException(Throwable cause) {
        super(cause);
    }
}