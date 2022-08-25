/**
 * 
 */
package com.zek.doorstep.exception;

import java.nio.file.InvalidPathException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zek.doorstep.pojos.ResponseModel;
import com.zek.doorstep.util.CommonConstant;

/**
 * @author EK
 *
 */
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ResponseModel> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseModel(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.name(),CommonConstant.FAILED,null));
  }
  
  @ExceptionHandler(InvalidPathException.class)
  public ResponseEntity<ResponseModel> handleInvalidPathException(InvalidPathException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.name(),CommonConstant.FAILED,null));
  }
  
//  @ExceptionHandler({MyFileNotFoundException.class})
//  public ResponseEntity<ResponseModel> handleInvalidPathException(MyFileNotFoundException exc) {
//    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.name(),CommonConstant.FAILED,null));
//  }
  
}