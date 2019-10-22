package main.java.entities;

public class Subject extends GenericEntity<String, Subject> {
  private String name;
  
  {

  }

  public Subject() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
