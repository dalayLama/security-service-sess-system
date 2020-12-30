package com.sess.security.core.services.http.transports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sess.security.core.services.exceptions.HttpCoreServiceException;
import com.sess.security.exceptions.Error;
import com.sess.security.exceptions.ErrorBuilder;
import com.sess.security.core.services.exceptions.CoreServiceException;
import com.sess.security.components.message.MessageId;
import com.sess.security.components.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class SimpleErrorHandler implements CoreServiceErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleErrorHandler.class);

    private final ObjectMapper objectMapper;

    private final MessageService messageService;

    public SimpleErrorHandler(ObjectMapper objectMapper, MessageService messageService) {
        this.objectMapper = objectMapper;
        this.messageService = messageService;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws CoreServiceException {
        throwException(response);
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws CoreServiceException {
        throwException(response);
    }

    private void throwException(ClientHttpResponse response) {
        try {
            Error error = objectMapper.readValue(response.getBody(), Error.class);
            throw new HttpCoreServiceException(response.getStatusCode(), error);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            Error error = ErrorBuilder.singletonMessage(false,
                    messageService.sayError(MessageId.UNREADABLE_CORE_ERROR_RESPONSE));
            try {
                throw new HttpCoreServiceException(response.getStatusCode(), error);
            } catch (IOException e2) {
                LOG.error(e2.getMessage(), e2);
                throw new HttpCoreServiceException(null, error);
            }
        }
    }

}
