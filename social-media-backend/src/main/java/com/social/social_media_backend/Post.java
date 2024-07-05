package com.social.social_media_backend;

import com.social.social_media_backend.models.SocialUser;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SocialUser socialUser;
}
