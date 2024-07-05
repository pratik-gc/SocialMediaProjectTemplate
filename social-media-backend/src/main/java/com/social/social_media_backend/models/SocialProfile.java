package com.social.social_media_backend.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "socialProfile") //this "socialProfile" is the field in SocialUser class
    //If we use mappedBy attribute, we can skip @JoinColumn here because only one foreign key will now be generated
    private SocialUser user;

    //SocialProfile class is the non-owning side of the bidirectional OneToOne relationship
    //non-owning side needs to use mappedBy attribute to tell that it is being managed by a field in owning side
    //So, don't create a column over here
}
