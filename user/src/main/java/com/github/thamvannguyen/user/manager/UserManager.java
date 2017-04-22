package com.github.thamvannguyen.user.manager;


import com.github.thamvannguyen.user.dao.entity.UserProfile;



public interface UserManager {

    UserProfile createOrUpdate(UserProfile userProfile);
    UserProfile findByfirstName(String firstName);
}
