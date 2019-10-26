package main.java.mvc.dao;

import main.java.entities.GenericEntity;

import java.io.Serializable;

public interface DaoInterface<K extends Serializable, T extends GenericEntity> {
  T create(K key);
  T read(K key);
  void update(T obj);
  void delete(T obj);
}
