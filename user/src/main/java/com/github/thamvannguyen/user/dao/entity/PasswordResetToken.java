package com.github.thamvannguyen.user.dao.entity;

import com.github.thamvannguyen.core.jpa.auditing.AbstractAuditableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken extends AbstractAuditableEntity<String> implements Serializable {
    private static final Integer EXPIRATION = 60*24;
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Column(name = "token")
	private  String token;
    @Column(name = "expiryDate")
	private  Date expiryDate;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(final String token) {
        this.setId(UUID.randomUUID().toString());
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(Account account, String token) {
        this.setId(UUID.randomUUID().toString());
        this.account = account;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiration) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiration);
        return new Date(calendar.getTime().getTime());
    }

    public void updateToken(final String token){
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Account getAccount() {
        return account;
    }


    public String getToken() {
        return token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "account=" + account +
                ", token='" + token + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
