package com.github.thamvannguyen.user.dao.repository;


import com.github.thamvannguyen.user.dao.entity.Account;
import com.github.thamvannguyen.user.dto.AccountDto;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByUsername(String username);
    Account findByEmail(String email);

    @Query("SELECT COUNT(u) FROM Account u WHERE u.id != :id and (u.username = :username or u.email = :email or u.phoneNumber =:phoneNumber) ")
    int countByUsernameOrEmailOrPhoneAndIdNotIn(@Param("username") String username, @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("id") String id);

}
