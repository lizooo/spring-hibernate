package com.iot.dto;

import com.iot.domain.Playlist;

import javax.persistence.Column;
import java.util.Set;

public class LabelDTO {
  private Integer id;
  private String name;
  private Set<Playlist> playlistSet;

  public LabelDTO(Integer id, String name, Set<Playlist> playlistSet) {
    this.id = id;
    this.name = name;
    this.playlistSet = playlistSet;
  }

  public Set<Playlist> getPlaylistSet() {
    return playlistSet;
  }

  public void setPlaylistSet(Set<Playlist> playlistSet) {
    this.playlistSet = playlistSet;
  }

  public LabelDTO() {
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
