package main.java.entities;

public class Teacher extends GenericEntity<String, Teacher> {
  private String name;
  private Subject subject;

  {
    
  }
  
  public Teacher() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
