package com.social.social_media_backend.services;

import com.social.social_media_backend.models.SocialUser;
import com.social.social_media_backend.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllUsers() {
        return socialUserRepository.findAll();
    }

    public SocialUser saveNewUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }
}
