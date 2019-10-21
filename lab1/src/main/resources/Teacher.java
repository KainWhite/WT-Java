package main.resources;

public class Teacher implements Identifiable {
  {
    
  }
  
  public Teacher() {}

  public String getId() {
    return name;
  }

  public void setId(String name) {
    this.name = name;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  private String name;
  private Subject subject;
}
