package main.java.entities;

import java.io.Serializable;

public interface Identifiable<K extends Serializable> {
  K getId();
  
  void setId(K name);
}
