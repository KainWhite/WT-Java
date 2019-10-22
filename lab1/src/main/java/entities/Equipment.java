package main.java.entities;

public class Equipment extends GenericEntity<String, Equipment> {
  private String name;

  {

  }

  public Equipment() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
