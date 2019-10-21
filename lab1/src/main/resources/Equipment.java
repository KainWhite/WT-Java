package main.resources;

public class Equipment implements Identifiable {
  {

  }

  public Equipment() {}

  public String getId() {
    return name;
  }

  public void setId(String name) {
    this.name = name;
  }

  private String name;
}
