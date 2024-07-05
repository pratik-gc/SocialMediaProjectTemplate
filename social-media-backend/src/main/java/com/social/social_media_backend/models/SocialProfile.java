package com.social.social_media_backend.models;

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
    private SocialUser user;

    //SocialProfile is the owner of OneToOne relationship & socialProfile field is managing this bidirectional relationship
    //@JoinColumn is only used in the owning side


    public SocialProfile(Long id, SocialUser user) {
        this.id = id;
        this.user = user;
    }

    public SocialProfile(){
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

    public void setUser(SocialUser user) {
        this.user = user;
    }
}
