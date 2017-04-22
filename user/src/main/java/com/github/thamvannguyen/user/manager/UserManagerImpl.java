package com.github.thamvannguyen.user.manager;

import com.github.thamvannguyen.user.dao.entity.UserProfile;
import com.github.thamvannguyen.user.dao.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile createOrUpdate(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile findByfirstName(String firstName) {
        return userProfileRepository.findByFirstName(firstName);
    }


}
