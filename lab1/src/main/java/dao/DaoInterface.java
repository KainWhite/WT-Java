package main.java.dao;

import main.resources.Identifiable;

import java.io.Serializable;

public interface DaoInterface<PK extends Serializable, T extends Identifiable<PK>> {
  public T create();
  public T read(PK key);
  public void update(T object);
  public void delete(T object);
}
