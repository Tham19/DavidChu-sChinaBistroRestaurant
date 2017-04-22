package com.github.thamvannguyen.user.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.thamvannguyen.core.jpa.auditing.AbstractAuditableEntity;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "account")
public class Account extends AbstractAuditableEntity<String> implements UserDetails {
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "account_role", joinColumns = { @JoinColumn(name = "accountId") }, inverseJoinColumns = {
            @JoinColumn(name = "roleId") })
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private PasswordHistory passwordHistory;

    @Column(name = "username")
    @Size(max = 45)
    @NotEmpty
    private String username;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @Email
//    @Max(100)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;
    private boolean enabled = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access_timestamp")
    private Date lastAccessTimestamp;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activation_time")
    private Date activationTime;

    public Account() {
        this.setId(UUID.randomUUID().toString());
        passwordHistory = new PasswordHistory(this.getId());
    }

    public Account(String username, String password) {
        this.setId(UUID.randomUUID().toString());
        passwordHistory = new PasswordHistory(this.getId());
        this.username = username;
        this.password = password;
    }

    public void resetAccountNonLocked(){
        this.accountNonLocked = true;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public PasswordHistory getPasswordHistory() {
        return passwordHistory;
    }

    public Date getLastAccessTimestamp() {
        return lastAccessTimestamp;
    }

    public void setLastAccessTimestamp(Date lastAccessTimestamp) {
        this.lastAccessTimestamp = lastAccessTimestamp;
    }

    public Date getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPasswordHistory(PasswordHistory passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    @Override
    public String toString() {
        return "Account{" +
                "roles=" + roles +
                ", passwordHistory=" + passwordHistory +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accountNonLocked=" + accountNonLocked +
                ", enabled=" + enabled +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonExpired=" + accountNonExpired +
                ", lastAccessTimestamp=" + lastAccessTimestamp +
                ", activationTime=" + activationTime +
                '}';
    }
}
