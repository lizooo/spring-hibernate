package com.iot.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface AbstractService<E, ID> {

  public List<E> getAll();

  public E getById(ID id);

  public E create(E newObject);

  public E update(ID id, E object);

  public void deleteById(ID id);

}