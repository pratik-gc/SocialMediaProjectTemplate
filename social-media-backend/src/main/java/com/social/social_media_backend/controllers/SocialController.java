package com.social.social_media_backend.controllers;

import com.social.social_media_backend.models.SocialUser;
import com.social.social_media_backend.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers(){
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialService.saveNewUser(socialUser), HttpStatus.CREATED);
    }

}
