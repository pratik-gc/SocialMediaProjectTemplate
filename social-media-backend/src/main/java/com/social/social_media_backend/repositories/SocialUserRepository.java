package com.social.social_media_backend.repositories;

import com.social.social_media_backend.models.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {
}
