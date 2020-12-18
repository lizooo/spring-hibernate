package com.iot.repository;

import com.iot.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
  List<Playlist> getPlaylistsByGenreId(Integer genre_id);
  List<Playlist> getPlaylistsByLabelId(Integer label_id);
  List<Playlist> getPlaylistsByUserId(Integer user_id);
}
