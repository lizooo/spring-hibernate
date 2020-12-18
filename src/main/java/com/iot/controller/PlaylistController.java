package com.iot.controller;


import com.iot.domain.Credentials;
import com.iot.domain.Playlist;
import com.iot.dto.PlaylistDTO;
import com.iot.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/playlist")
@RestController

public class PlaylistController {
  private final PlaylistService playlistService;

  public PlaylistController(PlaylistService playlistService){
    this.playlistService = playlistService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<PlaylistDTO>> getAll() {
    List<Playlist> playlists = playlistService.getAll();
    List<PlaylistDTO> playlistDTOs = new ArrayList<>();
    for (Playlist playlist : playlists) {
      PlaylistDTO userDTO = new PlaylistDTO (
          playlist.getId(),
          playlist.getName(),
          playlist.getNumberOfSongs(),
          playlist.getGenreId(),
          playlist.getLabelId(),
          playlist.getUserId()
      );
      playlistDTOs.add(userDTO);
    }
    return new ResponseEntity<>(playlistDTOs, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<PlaylistDTO> getById(@PathVariable Integer id) {
    Playlist playlist = playlistService.getById(id);
    if (playlist != null) {
      PlaylistDTO labelDto = new PlaylistDTO(
          playlist.getId(),
          playlist.getName(),
          playlist.getNumberOfSongs(),
          playlist.getGenreId(),
          playlist.getLabelId(),
          playlist.getUserId()
      );
      return new ResponseEntity<>(labelDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Playlist newLabel) {
    playlistService.create(newLabel);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<PlaylistDTO> update(@PathVariable Integer id,
                                         @RequestBody Playlist playlist) {
    Playlist oldLabel = playlistService.getById(id);
    if (oldLabel != null) {
      playlistService.update(id, playlist);
      PlaylistDTO oldLabelDTO = new PlaylistDTO(
          playlist.getId(),
          playlist.getName(),
          playlist.getNumberOfSongs(),
          playlist.getGenreId(),
          playlist.getLabelId(),
          playlist.getUserId()
      );
      return new ResponseEntity<>(oldLabelDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (playlistService.getById(id) != null) {
      playlistService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }



}

