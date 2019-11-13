package main.java.mvc.view;

import main.java.entities.GenericEntity;

import java.lang.reflect.Field;

public class View {
  public View() {
  }
  
  public void displayObject(GenericEntity obj) {
    System.out.print(obj);
  }
  
  public void displayFields(Field[] fields) {
    for (int i = 1; i <= fields.length; i++) {
      System.out.printf("%d. %s\n", i, fields[i - 1].getName());
    }
  }
}
