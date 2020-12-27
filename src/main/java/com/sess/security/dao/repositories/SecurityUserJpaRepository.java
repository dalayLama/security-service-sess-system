package com.sess.security.dao.repositories;

import com.sess.security.models.user.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserJpaRepository extends JpaRepository<SecurityUser, Long> {
}
