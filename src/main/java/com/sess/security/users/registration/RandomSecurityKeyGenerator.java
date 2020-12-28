package com.sess.security.users.registration;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomSecurityKeyGenerator implements SecurityKeyGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

}
