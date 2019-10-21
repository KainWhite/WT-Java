package main.java.interfaces;

import java.io.Serializable;

public interface Identifiable<PK extends Serializable> {
  public PK getId();
  public void setId(PK name);
}
