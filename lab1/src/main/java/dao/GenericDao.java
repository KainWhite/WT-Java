package main.java.dao;

import main.resources.Identifiable;

import java.io.Serializable;

public abstract class GenericDao<PK extends Serializable, T extends Identifiable<PK>> 
    implements DaoInterface<PK, T> {
  @Override
  public T create() {
    return null;
  }

  @Override
  public T read(PK key) {
    return null;
  }

  @Override
  public void update(T object) {

  }

  @Override
  public void delete(T object) {

  }
}
