package com.nagarro.roomservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(RoomNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleHotelManagementException(RoomNotFoundException ex) {
    ErrorResponse error = new ErrorResponse();
    error.setErrorCode(ex.getErrorCode());
    error.setMessage(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorResponse error = new ErrorResponse();
    error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage("Something went wrong, please try again later.");
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

