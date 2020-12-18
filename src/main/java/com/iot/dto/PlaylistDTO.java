package com.iot.dto;

import javax.persistence.Column;

public class PlaylistDTO {

  private Integer id;
  private String name;
  private Integer numberOfSongs;
  private Integer genreId;
  private Integer labelId;
  private Integer userId;

  public PlaylistDTO(Integer id, String name, Integer numberOfSongs, Integer genreId, Integer labelId, Integer userId) {
    this.id = id;
    this.name = name;
    this.numberOfSongs = numberOfSongs;
    this.genreId = genreId;
    this.labelId = labelId;
    this.userId = userId;
  }

  public PlaylistDTO() {
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

  public Integer getNumberOfSongs() {
    return numberOfSongs;
  }

  public void setNumberOfSongs(Integer numberOfSongs) {
    this.numberOfSongs = numberOfSongs;
  }

  public Integer getGenreId() {
    return genreId;
  }

  public void setGenreId(Integer genreId) {
    this.genreId = genreId;
  }

  public Integer getLabelId() {
    return labelId;
  }

  public void setLabelId(Integer labelId) {
    this.labelId = labelId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
