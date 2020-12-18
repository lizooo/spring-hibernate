package com.iot.service;

import com.iot.domain.Credentials;
import com.iot.repository.CredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService implements AbstractService<Credentials, Integer> {
  private final CredentialsRepository credentialsRepository;

  public CredentialsService(CredentialsRepository credentialsRepository){
    this.credentialsRepository = credentialsRepository;
  }


  @Override
  public List<Credentials> getAll() {
    return credentialsRepository.findAll();
  }

  @Override
  public Credentials getById(Integer id) {
    return credentialsRepository.getOne(id);
  }

  @Override
  public Credentials create(Credentials newObject) {
    return credentialsRepository.save(newObject);
  }

  @Override
  public Credentials update(Integer id, Credentials object) {
    if (credentialsRepository.findById(id).isPresent()) {
      return credentialsRepository.save(object);
    } else {
      return null;
    }

  }

  @Override
  public void deleteById(Integer id) {
    if (credentialsRepository.findById(id).isPresent()) {
      credentialsRepository.deleteById(id);
    }
  }
}
