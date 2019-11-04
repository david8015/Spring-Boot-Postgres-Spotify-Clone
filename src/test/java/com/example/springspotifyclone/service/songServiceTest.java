package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.repositories.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class songServiceTest {
    @Mock
    SongRepository songRepository;

    @InjectMocks
    Song song;

    @InjectMocks
    SongServiceImpl songService;

    @Before
    public void initializeDummyUser(){
        song.setId(1L);
        song.setTitle("gone till november");
        song.setLength("4:15");
    }

    @Test
    public void createSong_Song_Success() {

        when(songRepository.save(any())).thenReturn(song);

        Song tempSong = songService.createSong(song);

        assertEquals(song.getTitle(), tempSong.getTitle());
    }
}
