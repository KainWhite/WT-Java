package main.java.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import main.java.xmlentitylists.XmlSubjectList;

public class Teacher extends GenericEntity<String, Teacher> {
  private String name;
  @JacksonXmlProperty(localName = "Subjects")
  private XmlSubjectList subjects;
  
  {
    name = null;
    subjects = new XmlSubjectList();
  }
  
  public Teacher() {
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Teacher\n");
    str.append(super.toString());
    if (name != null) {
      str.append(" name: ").append(name).append("\n");
    }
    if (subjects != null && subjects.size() != 0) {
      str.append(" subjects:\n");
      for (Subject subject : subjects.getEntities()) {
        if (subject != null) {
          str.append(subject.toString(2));
        }
      }
    }
    return str.toString();
  }
  
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Teacher\n");
    str.append(super.toString(identSpacesCount));
    if (name != null) {
      str.append(spaces).append(" name: ").append(name).append("\n");
    }
    if (subjects != null && subjects.size() != 0) {
      str.append(spaces).append(" subjects:\n");
      for (Subject subject : subjects.getEntities()) {
        if (subject != null) {
          str.append(subject.toString(identSpacesCount + 2));
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
  
  public XmlSubjectList getSubjects() {
    return subjects;
  }
  
  public void setSubjects(XmlSubjectList subjects) {
    this.subjects = subjects;
  }
}
