package com.social.social_media_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user")
    @JsonIgnore
    private SocialUser user;
    //SocialProfile is the owner of OneToOne relationship & socialProfile field is managing this bidirectional relationship
    //@JoinColumn is only used in the owning side

    private String description;

    public SocialProfile(){
    }

//    public SocialProfile(Long id, SocialUser user) {
//        this.id = id;
//        this.user = user;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public SocialUser getUser() {
//        return user;
//    }
//
//    public void setUser(SocialUser user) {
//        this.user = user;
//    }


    public SocialProfile(Long id, SocialUser user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialUser getUser() {
        return user;
    }

//    public void setUser(SocialUser user) {
//        this.user = user;
//    }

    //Customezed Setter for setting user field
    //Writing customized Setter manually is helpful in order to maintain the consistency on both the sides
    //Therefore, it is a good practice to write your own custom Setters on both sides of the relationship
    public void setSocialUser(SocialUser socialUser){
        this.user = socialUser;
        if (user.getSocialProfile() != this)
            user.setSocialProfile(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
