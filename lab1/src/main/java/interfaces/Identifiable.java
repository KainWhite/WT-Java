package main.java.interfaces;

import java.io.Serializable;

public interface Identifiable<K extends Serializable> {
  K getId();
  
  void setId(K name);
}
