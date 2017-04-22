package com.github.thamvannguyen.user.dao.repository;


import com.github.thamvannguyen.core.jpa.repository.AuditingRepository;
import com.github.thamvannguyen.user.dao.entity.UserProfile;



public interface UserProfileRepository extends AuditingRepository<UserProfile, String> {
    UserProfile findByFirstName(String firstName);
}
