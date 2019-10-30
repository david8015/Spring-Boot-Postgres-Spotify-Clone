package com.example.springspotifyclone.repositories;

import com.example.springspotifyclone.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Long> {

}
