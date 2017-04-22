package com.github.thamvannguyen.user.dao.repository;

import com.github.thamvannguyen.user.dao.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
    PasswordResetToken findByToken(String token);
}
