package com.sess.security.core.services.http.transports;

import com.sess.security.core.services.exceptions.CoreServiceException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import java.net.URI;

public interface CoreServiceErrorHandler extends ResponseErrorHandler {

    @Override
    void handleError(ClientHttpResponse response) throws CoreServiceException;

    @Override
    void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws CoreServiceException;
}
