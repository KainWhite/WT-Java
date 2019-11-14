package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Parent class for complex entities
 *
 * @param <K> id type
 * @param <T> entity class
 */
public abstract class ComplexEntity<K extends Serializable,
    T extends Identifiable<K> & Comparable<T>>
    extends GenericEntity<K, T> {
  @JsonIgnore
  public abstract List<List<GenericEntity>> getInternalEntityLists();
  
  @JsonIgnore
  public abstract List<GenericEntity> getInternalEntities();
  
  @JsonIgnore
  public abstract void setInternalEntities(List<GenericEntity> internalEntities);
}
