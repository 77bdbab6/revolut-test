package com.company.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(RuntimeException exception) {
    return Response
        .serverError()
        .entity(new ErrorMessage(exception))
        .build();
  }

  public static class ErrorMessage {
    private String message;

    public ErrorMessage(Exception e) {
      this.message = e.getMessage();
    }

    public String getMessage() {
      return message;
    }
  }
}
