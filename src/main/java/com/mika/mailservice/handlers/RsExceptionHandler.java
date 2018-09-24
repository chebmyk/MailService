package com.mika.mailservice.handlers;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class RsExceptionHandler implements ExceptionMapper<Throwable> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable ex) {
        log.error(ex.getMessage());
        Response.StatusType httpStatus = getHttpStatus(ex);
        ErrorDetails errorDetails = new ErrorDetails.ErrorDetailsBuilder()
                .message(httpStatus.getReasonPhrase())
                .details(ex.getMessage())
                .path(uriInfo.getPath())
                .build();

        return Response.status(httpStatus.getStatusCode())
                .entity(errorDetails)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    private Response.StatusType getHttpStatus(Throwable ex) {
        Response.StatusType result;
        if (ex instanceof WebApplicationException) {
            result = ((WebApplicationException) ex).getResponse().getStatusInfo();
        } else {
            result = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return result;
    }
}
