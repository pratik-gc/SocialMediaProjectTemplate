package com.social.social_media_backend.repositories;

import com.social.social_media_backend.models.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {
}
