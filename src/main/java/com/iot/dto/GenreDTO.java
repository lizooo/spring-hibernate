package com.iot.dto;

import com.iot.domain.Playlist;

import java.util.Set;

public class GenreDTO {
  private Integer id;
  private String name;
  private Set<Playlist> playlistName;

  public GenreDTO(Integer id, String name, Set<Playlist> playlistName) {
    this.id = id;
    this.name = name;
    this.playlistName = playlistName;
  }

  public Set<Playlist> getPlaylistName() {
    return playlistName;
  }

  public void setPlaylistName(Set<Playlist> playlistName) {
    this.playlistName = playlistName;
  }

  public GenreDTO() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

