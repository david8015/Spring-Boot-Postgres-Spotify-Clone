package com.example.springspotifyclone.repositories;

import com.example.springspotifyclone.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("FROM User u WHERE u.username = ?1 and u.password = ?2")
    public User login(String username, String password);

    public User findByUsername(String username);

//    @Query("Delete from user_song us where us.userId =?1 and us.songId =?2 ")
//    public void removeSongById(Long userId, Long songId);

}