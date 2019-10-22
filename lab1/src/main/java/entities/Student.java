package main.java.entities;

public class Student extends GenericEntity<String, Student> {
  private String name;
  private int form;

  {

  }

  public Student() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getForm() {
    return form;
  }

  public void setForm(int form) {
    this.form = form;
  }
}
