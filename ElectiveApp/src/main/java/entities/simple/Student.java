package main.java.entities.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.entities.GenericEntity;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Student extends GenericEntity<String, Student> {
  private String name;
  private int form;
  
  {
    name = null;
    form = -1;
  }
  
  @Override
  public Student createNewInstance() {
    Student obj = new Student();
    obj.setId(this.getId());
    return obj;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Student\n");
    str.append(super.toString());
    if (name != null) {
      str.append(" name: ").append(name).append("\n");
    }
    if (form != -1) {
      str.append(" form: ").append(form).append("\n");
    }
    return str.toString();
  }
  
  @Override
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Student\n");
    str.append(super.toString(identSpacesCount));
    if (name != null) {
      str.append(spaces).append(" name: ").append(name).append("\n");
    }
    if (form != -1) {
      str.append(spaces).append(" form: ").append(form).append("\n");
    }
    return str.toString();
  }
  
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
