package main.resources;

public class Subject implements Identifiable {
  {

  }

  public Subject() {}

  public String getId() {
    return name;
  }

  public void setId(String name) {
    this.name = name;
  }

  private String name;
}
