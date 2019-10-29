package main.java.mvc;

import main.java.entities.GenericEntity;

public class View {
  View() {
  }
  
  public void displayObject(GenericEntity obj) {
    System.out.print(obj.toString());
  }
}
