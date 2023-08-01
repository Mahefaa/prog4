package com.studies.prog4.controller.view.model.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends ServerException {
  public NotFoundException(String message) {
    super(message, NOT_FOUND.value());
  }
}
