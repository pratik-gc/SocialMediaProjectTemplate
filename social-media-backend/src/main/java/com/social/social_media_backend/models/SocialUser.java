package com.social.social_media_backend.models;

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

    @OneToOne(mappedBy = "user") //this "user" is the field in SocialProfile class
    //@JoinColumn(name = "social_profile_id")
    //If we use mappedBy attribute, we can skip @JoinColumn here because only one foreign key will now be generated
    private SocialProfile socialProfile;

    //SocialUser class is the non-owning side of the bidirectional OneToOne relationship
    //non-owning side needs to use mappedBy attribute to tell that it is being managed by a field in owning side
    //So, don't create a column over here

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
