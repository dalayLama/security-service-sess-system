package com.sess.security.dao.repositories;

import com.sess.security.models.user.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityUserJpaRepository extends JpaRepository<SecurityUser, Long> {

    Optional<SecurityUser> findByEmail(String email);

    Optional<SecurityUser> findByTelegramDataId(int telegramId);

}
