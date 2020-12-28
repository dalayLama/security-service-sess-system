package com.sess.security.api.rest;

import com.sess.security.models.user.TelegramUser;
import com.sess.security.users.registration.RegistrationManager;
import com.sess.security.users.registration.exceptions.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegistrationUsers {

    private final RegistrationManager registrationManager;

    public RegistrationUsers(RegistrationManager registrationManager) {
        this.registrationManager = registrationManager;
    }

    @PostMapping(value = "/telegram")
    public ResponseEntity<Object> registrationAsTelegram(@RequestBody TelegramUser telegramUser) {
        try {
            registrationManager.register(telegramUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ValidateException e) {
            return new ResponseEntity<>(String.join("\n", e.getErrors()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
