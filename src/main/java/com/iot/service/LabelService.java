package com.iot.service;

import com.iot.domain.Label;
import com.iot.repository.GenreRepository;
import com.iot.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService implements AbstractService<Label, Integer>{
  private final LabelRepository labelRepository;

  public LabelService(LabelRepository labelRepository){
    this.labelRepository = labelRepository;
  }


  @Override
  public List<Label> getAll() {
    return labelRepository.findAll();
  }

  @Override
  public Label getById(Integer id) {
    return labelRepository.getOne(id);
  }

  @Override
  public Label create(Label newObject) {
    return labelRepository.save(newObject);
  }

  @Override
  public Label update(Integer id, Label object) {
    if (labelRepository.findById(id).isPresent()) {
      return labelRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (labelRepository.findById(id).isPresent()) {
      labelRepository.deleteById(id);
    }

  }
}
