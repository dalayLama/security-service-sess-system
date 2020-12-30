package com.sess.security.api.rest;

import com.sess.security.core.services.exceptions.HttpCoreServiceException;
import com.sess.security.models.user.RegisteredCoreUser;
import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.RegistrationManager;
import com.sess.security.users.registration.exceptions.RegistrationException;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegistrationUsersRestController {

    private final RegistrationManager registrationManager;

    public RegistrationUsersRestController(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping(value = "/telegram")
    public ResponseEntity<Object> registrationAsTelegram(@RequestBody TelegramUser telegramUser) {
        try {
            RegisteredCoreUser result = registrationManager.telegramRegister(telegramUser);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (ValidateException e) {
            return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
        } catch (RegistrationException e) {
            if (e.hasCoreException() && e.getCoreException() instanceof HttpCoreServiceException) {
                HttpCoreServiceException httpException = (HttpCoreServiceException) e.getCoreException();
                return new ResponseEntity<>(e.getError(), httpException.getStatus());
            }
            return new ResponseEntity<>(e.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
