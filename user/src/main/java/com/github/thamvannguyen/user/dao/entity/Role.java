package com.github.thamvannguyen.user.dao.entity;

import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;
    //mapping relationship many to many to account
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Account> accounts;


    public enum ROLE {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @Column(name = "id")
    private Integer id = 1;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ROLE role = ROLE.ROLE_USER;

    public Role() {
        super();
    }



    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
