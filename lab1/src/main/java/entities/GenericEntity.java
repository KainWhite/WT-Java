package main.java.entities;

import main.java.interfaces.Identifiable;

import java.io.Serializable;

public class GenericEntity<
                                     K extends Serializable,
                                     T extends Identifiable<K> & Comparable<T>
                                   >
    implements Identifiable<K>, Comparable<T> {
  private K id;
  
  public GenericEntity() {}
  
  @Override
  public int compareTo(T obj) {
    return id.toString()
        .compareTo(obj.getId().toString()); // TODO: 22.10.2019 compare by className+id
  }

  @Override
  public K getId() {
    return id;
  }

  @Override
  public void setId(K id) {
    this.id = id;
  }
}
