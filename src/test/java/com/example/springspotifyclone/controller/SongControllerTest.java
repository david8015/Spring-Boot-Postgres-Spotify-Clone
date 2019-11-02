package com.example.springspotifyclone.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.springspotifyclone.Controller.SongController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.service.SongService;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {



    private MockMvc mockMvc;
    private List<Song> songs;

    @InjectMocks
    private Song createdSong;


    @Mock
    private SongService songService;


    @InjectMocks
    SongController songController;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
        createdSong.setTitle("hey joe");
        createdSong.setLength("162");
    }

    private static String createSongInJson(String title, String length) {
        return "{ \"title\": \"" + title + "\", " +
                "\"length\":" + length + "}";
    }

    @Test
    public void createSong_Song_Success() throws Exception {


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/song")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createSongInJson("hey joe","162"));

        when(songService.createSong(any())).thenReturn(createdSong);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(createSongInJson("hey joe","162")));
        verify(songService, times(1)).createSong(any());
    }

    @Test
    public void listSongs_Songs_Success() throws Exception{
        songs = new ArrayList<Song>();

        Song song1 = new Song();
        Song song2 = new Song();
        song1.setTitle("Hey Jude");
        song1.setLength("320");
        song2.setTitle("Yellow Submarine");
        song2.setLength("207");
        songs.add(song1);
        songs.add(song2);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/song/list");


        when(songService.listSongs()).thenReturn(songs);
        ObjectMapper mapper = new ObjectMapper();
        String songsJsonStr = mapper.writeValueAsString(songs);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(songsJsonStr));
        verify(songService, times(1)).listSongs();
    }



}