package br.com.empresa.exceptions;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<String> mapException(Exception e) {
        return RestResponse.status(RestResponse.Status.NOT_FOUND, "Error: " + e.getMessage());
    }

}
