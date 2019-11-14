package main.java.entities;

import java.io.Serializable;

/**
 * Interface for entities with id
 *
 * @param <K> id type
 */
public interface Identifiable<K extends Serializable> {
  K getId();
  
  void setId(K name);
}
