package main.resources;

public class Student implements Identifiable {
  {

  }

  public Student() {}

  public String getId() {
    return name;
  }

  public void setId(String name) {
    this.name = name;
  }

  public int getForm() {
    return form;
  }

  public void setForm(int form) {
    this.form = form;
  }

  private String name;
  private int form;
}
