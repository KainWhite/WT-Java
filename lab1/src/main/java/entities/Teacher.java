package main.java.entities;

import java.util.ArrayList;

public class Teacher extends GenericEntity<String, Teacher> {
  private String name;
  private ArrayList<Subject> subjects;

  {
    name = null;
    subjects = new ArrayList<>();
  }
  
  public Teacher() {}

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Teacher\n");
    str.append(super.toString());
    if(name != null) {
      str.append(" name: ").append(name).append("\n");
    }
    if(subjects != null && subjects.size() != 0) {
      str.append(" subjects:\n");
      for (Subject subject : subjects) {
        if(subject != null) {
          str.append(subject.toString(1));
        }
      }
    }
    return str.toString();
  }
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Teacher\n")
        .append(super.toString(identSpacesCount));
    if(name != null) {
      str.append(spaces).append(" name: ").append(name).append("\n");
    }
    if(subjects != null && subjects.size() != 0) {
      str.append(" subjects:\n");
      for (Subject subject : subjects) {
        if(subject != null) {
          str.append(spaces)
              .append(subject.toString(identSpacesCount + 1));
        }
      }
    }
    return str.toString();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(ArrayList<Subject> subjects) {
    this.subjects = subjects;
  }
}
