package com.social.social_media_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
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

  //================ Lombok Library doesn't work=======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<SocialGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<SocialGroup> groups) {
        this.groups = groups;
    }

    public SocialUser() {
    }

    public SocialUser(Long id, SocialProfile socialProfile, List<Post> posts, Set<SocialGroup> groups) {
        this.id = id;
        this.socialProfile = socialProfile;
        this.posts = posts;
        this.groups = groups;
    }

    //If we don't override hashcode(), we will get StackOverflowError
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
