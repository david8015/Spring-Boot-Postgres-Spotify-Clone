package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.Song;

public interface SongService {
    public Song createSong(Song song);
    public Iterable<Song> listSongs();
}
