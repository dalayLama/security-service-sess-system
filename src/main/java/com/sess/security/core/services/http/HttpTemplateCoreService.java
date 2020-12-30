package com.sess.security.core.services.http;

import com.sess.security.core.services.CoreService;
import com.sess.security.core.services.exceptions.CoreServiceException;
import com.sess.security.core.services.http.transports.HttpCoreTransport;
import com.sess.security.models.user.CoreUser;
import com.sess.security.models.user.RegisteredCoreUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HttpTemplateCoreService implements CoreService {

    private final HttpCoreTransport httpCoreTransport;

    private final String registrationUrn;

    public HttpTemplateCoreService(
            HttpCoreTransport httpCoreTransport,
            @Value("app.services.core.registration-user.urn") String registrationUrn) {
        this.httpCoreTransport = httpCoreTransport;
        this.registrationUrn = registrationUrn;
    }

    @Override
    public RegisteredCoreUser register(CoreUser coreUser) throws CoreServiceException {
        return httpCoreTransport.post(registrationUrn, coreUser, RegisteredCoreUser.class);
    }

}
