package com.sess.security.models;

import com.sess.security.models.user.SecurityUser;

import javax.persistence.*;

@Entity
@Table(name = "telegram_data")
public class TelegramData {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "telegram_data_id_seq"
    )
    @SequenceGenerator(
            name = "telegram_data_id_seq",
            sequenceName = "telegram_data_id_seq",
            allocationSize = 1
    )
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", unique = true, nullable = false, updatable = false)
    private SecurityUser user;

    @Column(name = "telegram_id", unique = true, updatable = false)
    private Integer telegramId;

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecurityUser getUser() {
        return user;
    }

    public void setUser(SecurityUser user) {
        this.user = user;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }
}
