package com.example.springspotifyclone.Controller;

import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public Song createSong(@RequestBody Song song){
        return songService.createSong(song);
    }

    @GetMapping("/list")
    public Iterable<Song> songList (){
        return songService.listSongs();
    }
}
