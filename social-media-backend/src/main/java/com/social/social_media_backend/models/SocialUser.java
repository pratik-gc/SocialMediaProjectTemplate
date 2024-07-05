package com.social.social_media_backend.models;

import com.social.social_media_backend.SocialGroup;
import com.social.social_media_backend.Post;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile_id")
    private SocialProfile socialProfile;
    //SocialUser is the owner of OneToOne relationship & socialProfile field is managing this bidirectional relationship
    //@JoinColumn is only used in the owning side

    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_group", //Name of the JoinTable
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> groups = new HashSet<>();
}
