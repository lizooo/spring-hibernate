package com.iot.controller;


import com.iot.domain.Genre;
import com.iot.dto.GenreDTO;
import com.iot.dto.PlaylistDTO;
import com.iot.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/genre")
@RestController

public class GenreController {
  private final GenreService genreService;

  public GenreController(GenreService genreService){
    this.genreService = genreService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<GenreDTO>> getAll() {
    List<Genre> genres = genreService.getAll();
    List<GenreDTO> labelDTOs = new ArrayList<>();
    for (Genre genre : genres) {
      GenreDTO userDTO = new GenreDTO (
          genre.getId(),
          genre.getName(),
          genre.getPlaylist()
      );
      labelDTOs.add(userDTO);
    }
    return new ResponseEntity<>(labelDTOs, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<GenreDTO> getById(@PathVariable Integer id) {
    Genre genre = genreService.getById(id);
    if (genre != null) {
      GenreDTO labelDto = new GenreDTO(
          genre.getId(),
          genre.getName(),
          genre.getPlaylist()
      );
      return new ResponseEntity<>(labelDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Genre newLabel) {
    genreService.create(newLabel);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<GenreDTO> update(@PathVariable Integer id,
                                         @RequestBody Genre genre) {
    Genre oldLabel = genreService.getById(id);
    if (oldLabel != null) {
      genreService.update(id, genre);
      GenreDTO oldLabelDTO = new GenreDTO(
          genre.getId(),
          genre.getName(),
          genre.getPlaylist()
      );
      return new ResponseEntity<>(oldLabelDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (genreService.getById(id) != null) {
      genreService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
