package com.social.social_media_backend.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user")
    private SocialUser user;

    //SocialProfile is the owner of OneToOne relationship & socialProfile field is managing this bidirectional relationship
    //@JoinColumn is only used in the owning side
}
