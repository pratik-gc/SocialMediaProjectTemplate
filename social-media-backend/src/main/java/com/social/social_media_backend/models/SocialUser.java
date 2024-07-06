package com.social.social_media_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne(mappedBy = "user") //this "user" is the field in SocialProfile class
    //@OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST) //PERSIST means only SAVE operation will be propagated
                                                                // and not the DELETE operation
    //@OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@OneToOne(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "social_profile_id")
    //If we use mappedBy attribute, we can skip @JoinColumn here because only one foreign key will now be generated
    //@JsonIgnore //@JsonIgnore is used to avoid infinite recursion which causes StackOverflowError
                  //If you add @JsonIgnore here, you will only see Id's of users and not other details
                  //Therefore, move @JsonIgnore to the other side of the bidirectional relationship
                  //@JsonIgnore is used to manage Circular References in bidirectional relationships
                  //@JsonIgnore enables us to get rid of the infinite nesting of objects when we are dealing with
                  //bidirectional relationships thus avoiding StackOverflowError.
                  //When we add @JsonIgnore to any side of the bidirectional relationship, it tells you to
                  // ignore that side from serialization & deserialization process from Object to JSON & vice-versa.
        //Cascading is an operation where changes/modifications in one entity involved in a bidrectional relationship
        //will propagate the same changes/modification in other entities that are in a relationship
        //CascadeType.ALL lets hibernate know that all delete/save/update operations, as the case may be, need to be
        //performed on all entities involved in the relationship.
    private SocialProfile socialProfile;

    //SocialUser class is the non-owning side of the bidirectional OneToOne relationship
    //non-owning side needs to use mappedBy attribute to tell that it is being managed by a field in owning side
    //So, don't create a column over here

    @OneToMany(mappedBy = "socialUser")
    //@JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_group", //Name of the JoinTable
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    //@JsonIgnore
    private Set<SocialGroup> groups = new HashSet<>();

  //========== Lombok Library doesn't work... Don't know why !!! ============
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

//    public void setSocialProfile(SocialProfile socialProfile) {
//        this.socialProfile = socialProfile;
//    }

    public void setSocialProfile(SocialProfile socialProfile){ //This customized setter is IMPORTANT bcz we are ensuring
                                            //that both sides of bidirectional relationship are aware of the changes
        socialProfile.setUser(this);
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
