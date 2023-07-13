package com.studies.prog4.controller.rest.mapper;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class ByteMapper {
  public String toBase64(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }
}
