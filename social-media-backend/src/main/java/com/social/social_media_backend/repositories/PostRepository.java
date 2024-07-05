package com.social.social_media_backend.repositories;

import com.social.social_media_backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
