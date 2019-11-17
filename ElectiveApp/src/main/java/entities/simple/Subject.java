package main.java.entities.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.entities.GenericEntity;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Subject extends GenericEntity<String, Subject> {
  private String name;
  
  {
    name = null;
  }
  
  @Override
  public Subject createNewInstance() {
    Subject obj = new Subject();
    obj.setId(this.getId());
    return obj;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Subject\n");
    str.append(super.toString());
    if (name != null) {
      str.append(" name: ").append(name).append("\n");
    }
    return str.toString();
  }
  
  @Override
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Subject\n").append(super.toString(
        identSpacesCount));
    if (name != null) {
      str.append(spaces).append(" name: ").append(name).append("\n");
    }
    return str.toString();
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
