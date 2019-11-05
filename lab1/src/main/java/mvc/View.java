package main.java.mvc;

import main.java.entities.GenericEntity;

import java.lang.reflect.Field;

public class View {
  View() {
  }
  
  public void displayObject(GenericEntity obj) {
    System.out.print(obj.toString());
  }
  
  public void displayFields(Field[] fields) {
    for (int i = 1; i <= fields.length; i++) {
      System.out.printf("%d. %s\n", i, fields[i - 1].getName());
    }
  }
}
