package com.iot.service;

import com.iot.domain.Genre;
import com.iot.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements AbstractService<Genre, Integer> {
  private final GenreRepository genreRepository;

  public GenreService(GenreRepository genreRepository){
    this.genreRepository = genreRepository;
  }

  @Override
  public List<Genre> getAll() {
    return genreRepository.findAll();
  }

  @Override
  public Genre getById(Integer id) {
    return genreRepository.getOne(id);
  }

  @Override
  public Genre create(Genre newObject) {
    return genreRepository.save(newObject);
  }

  @Override
  public Genre update(Integer id, Genre object) {
    if (genreRepository.findById(id).isPresent()){
      return genreRepository.save(object);
    } else {
      return null;
    }
  }

  @Override
  public void deleteById(Integer id) {
    if (genreRepository.findById(id).isPresent()) {
      genreRepository.deleteById(id);
    }
  }


}
