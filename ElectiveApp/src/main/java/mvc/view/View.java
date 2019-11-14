package main.java.mvc.view;

import main.java.entities.GenericEntity;

import java.lang.reflect.Field;

/**
 * Class for displaying changes
 */
public class View {
  public View() {
  }
  
  /**
   * @param obj object to display
   */
  public void displayObject(GenericEntity obj) {
    System.out.print(obj);
  }
  
  /**
   * @param fields field array to display
   */
  public void displayFields(Field[] fields) {
    for (int i = 1; i <= fields.length; i++) {
      System.out.printf("%d. %s\n", i, fields[i - 1].getName());
    }
  }
}
