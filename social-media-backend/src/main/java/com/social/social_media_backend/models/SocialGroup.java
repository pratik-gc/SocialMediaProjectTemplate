package com.social.social_media_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private Set<SocialUser> socialUsers = new HashSet<>();

    public SocialGroup() {
    }

    public SocialGroup(Long id, Set<SocialUser> socialUsers) {
        this.id = id;
        this.socialUsers = socialUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SocialUser> getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(Set<SocialUser> socialUsers) {
        this.socialUsers = socialUsers;
    }

    //If we don't override hashcode(), we will get StackOverflowError
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
