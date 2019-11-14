package main.java.entities.complex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import main.java.entities.ComplexEntity;
import main.java.entities.GenericEntity;
import main.java.entities.simple.Subject;
import main.java.xmlentitylists.simple.XmlSubjectList;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends ComplexEntity<String, Teacher> {
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
  
  @Override
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
  
  /**
   * @return ArrayList&lt;List&lt;GenericEntity&gt;&gt; with such order:
   * subjects
   */
  @Override
  public List<List<GenericEntity>> getInternalEntityLists() {
    List<List<GenericEntity>> internalEntityLists = new ArrayList<>();
    internalEntityLists.add((List<GenericEntity>) (List<?>) subjects
        .getEntities());
    return internalEntityLists;
  }
  
  /**
   * @return ArrayList&lt;GenericEntity&gt; with no elements
   */
  @Override
  public List<GenericEntity> getInternalEntities() {
    return new ArrayList<>();
  }
  
  @Override
  public void setInternalEntities(List<GenericEntity> internalEntities) {
    
  }
}
