package com.social.social_media_backend;

import com.social.social_media_backend.models.Post;
import com.social.social_media_backend.models.SocialGroup;
import com.social.social_media_backend.models.SocialProfile;
import com.social.social_media_backend.models.SocialUser;
import com.social.social_media_backend.repositories.PostRepository;
import com.social.social_media_backend.repositories.SocialGroupRepository;
import com.social.social_media_backend.repositories.SocialProfileRepository;
import com.social.social_media_backend.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    // Initializing field members with the help of parameterized constructor
    // We can also use @Autowired annotation above each of field members instead to initialize the field members by removing the parameterized constructor
    public DataInitializer(SocialUserRepository userRepository, SocialGroupRepository groupRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    //CommandLineRunner is a Functional Interface. This bean is executed when application context is initialized
    @Bean
    public CommandLineRunner initializeData(){
        return args -> { //It accepts an array of String arguments that we are passing in
            // Creating some Users everytime the app restarts
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            // Saving users into the database
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // Creating some groups
            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            //Adding users to the groups
            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user2);
            group2.getSocialUsers().add(user3);

            //Saving groups to the database
            groupRepository.save(group1);
            groupRepository.save(group2);

            // Associate users with groups
            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            //Saving users back to the database to update associations
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            //Creating some posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            //Associating posts with some users
            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            //Saving posts into the database
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // Creating some social profiles
            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            //Associating profiles with users
            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            //Saving profiles into the database
            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);



        };
    }
}
