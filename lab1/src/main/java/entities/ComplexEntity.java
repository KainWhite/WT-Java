package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.entityinterfaces.Identifiable;

import java.io.Serializable;
import java.util.List;

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
