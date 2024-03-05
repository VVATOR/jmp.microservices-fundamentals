package com.epam.learn.microservices.fundamental.services.gateway;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private static final String STATUS_ATTRIBUTE = "status";
    private static final String CODE_ATTRIBUTE = "code";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String TIMESTAMP_ATTRIBUTE = "timestamp";
    private static final String REQUEST_ID_ATTRIBUTE = "requestId";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> attributes = super.getErrorAttributes(request, options);

        final var error = super.getError(request);
        final var status = getHttpStatus(attributes);
        final var code = status.equals(HttpStatus.NOT_FOUND) ? HttpStatus.BAD_REQUEST.value() : status.value();

        attributes.put(CODE_ATTRIBUTE, code);
        attributes.put(MESSAGE_ATTRIBUTE, status.equals(HttpStatus.INTERNAL_SERVER_ERROR)
                ? "Don't give up! Try again a little bit later!"
                : error.getMessage());
        attributes.remove(STATUS_ATTRIBUTE);
        attributes.remove(TIMESTAMP_ATTRIBUTE);
        attributes.remove(REQUEST_ID_ATTRIBUTE);
        return attributes;
    }

    private HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get(STATUS_ATTRIBUTE);
        return HttpStatus.valueOf(statusCode);
    }
}