package com.studies.prog4.controller.view.model.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ServerException {
  public BadRequestException(String message) {
    super(message, BAD_REQUEST.value());
  }
}
