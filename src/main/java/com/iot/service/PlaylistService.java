package com.iot.service;

import com.iot.domain.Playlist;
import com.iot.repository.LabelRepository;
import com.iot.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements AbstractService<Playlist, Integer>{
  private final PlaylistRepository playlistRepository;

  public PlaylistService(PlaylistRepository playlistRepository){
    this.playlistRepository = playlistRepository;
  }


  @Override
  public List<Playlist> getAll() {
    return playlistRepository.findAll();
  }

  @Override
  public Playlist getById(Integer id) {
    return playlistRepository.getOne(id);
  }

  @Override
  public Playlist create(Playlist newObject) {
    return playlistRepository.save(newObject);
  }

  @Override
  public Playlist update(Integer id, Playlist object) {
    if (playlistRepository.findById(id).isPresent()) {
      return playlistRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (playlistRepository.findById(id).isPresent()) {
      playlistRepository.deleteById(id);
    }
  }

  public List<Playlist> getAllByGenreId(Integer id){
    return playlistRepository.getPlaylistsByGenreId(id);
  }

  public  List<Playlist> geAllByLabelId(Integer id){
    return playlistRepository.getPlaylistsByLabelId(id);
  }

  public List<Playlist> getAllByUserId(Integer id){
    return playlistRepository.getPlaylistsByUserId(id);
  }

}
