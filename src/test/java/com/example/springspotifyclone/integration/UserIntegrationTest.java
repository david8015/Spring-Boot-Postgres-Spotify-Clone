package com.example.springspotifyclone.integration;
import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.repositories.SongRepository;
import com.example.springspotifyclone.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springspotifyclone.repositories.UserRoleRepository;
import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.models.UserRole;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.springframework.dao.DataIntegrityViolationException;

    @ActiveProfiles("qa")
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class UserIntegrationTest {

        @Autowired
        UserRoleRepository userRoleRepository;

        private UserRole createUserRole(){
            UserRole userRole = userRoleRepository.findByName("ROLE_ADMIN");
            if(userRole == null){
                userRole = new UserRole();
                userRole.setName("ROLE_ADMIN");
                userRole = userRoleRepository.save(userRole);
            }
            return userRole;
        }

        private User createUser(){
            UserRole userRole = createUserRole();

            User user = new User();
            user.setUsername("batman");
            user.setPassword("bat");
            user.setUserRole(userRole);

            return user;
        }

        private Song createSong(){
            Song song = new Song();
            song.setLength("234");
            song.setTitle("No way");
            return song;
        }

        @Autowired
        UserRepository userRepository;

        @Autowired
        SongRepository songRepository;

        @Test
        public void signup_User_Success() {
            User user = userRepository.findByUsername("batman");
            if(user != null) {
                userRepository.delete(user);
            }
            user = createUser();
            user = userRepository.save(user);
            User foundUser = userRepository.findByUsername(user.getUsername());

            assertNotNull(user);
            assertNotNull(foundUser);
            assertEquals(user.getId(), foundUser.getId());

            userRepository.delete(user);
        }

        @Test(expected = DataIntegrityViolationException.class)
        public void signup_DuplicateUsername_Exception() {
            User user = createUser();
            userRepository.save(user);
            user.setId(null);
            userRepository.save(user);
        }

        @Test
        public void deleteById_User_Success(){
            User user = userRepository.findByUsername("batman");
            if(user==null) {
                createUser();
                user = userRepository.save(user);
            }
            userRepository.deleteById(user.getId());
            User foundUser = userRepository.findById(user.getId()).orElse(null);

            assertNull(foundUser);
        }

        @Test
        public void addCourse_User_Success() {
            User user = userRepository.findByUsername("batman");
            if(user == null) {
                user = createUser();
                user = userRepository.save(user);
            }
            Song song = songRepository.save(createSong());

            user.addSong(song);

            userRepository.save(user);

            assertNotNull(user);


        }


    }

