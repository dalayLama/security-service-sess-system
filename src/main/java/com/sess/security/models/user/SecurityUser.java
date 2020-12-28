package com.sess.security.models.user;

import com.sess.security.models.TelegramData;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * The user who is registered in the security service
 */
@Entity
@Table(name = "users")
public class SecurityUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_id_seq"
    )
    @SequenceGenerator(
            name = "users_id_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "email", unique = true, length = 128)
    private String email;

    @Column(name = "security_key", unique = true, length = 100)
    @Type(type="uuid-char")
    private UUID securityKey;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private TelegramData telegramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(UUID securityKey) {
        this.securityKey = securityKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TelegramData getTelegramData() {
        return telegramData;
    }

    public void setTelegramData(TelegramData telegramData) {
        this.telegramData = telegramData;
    }
}
