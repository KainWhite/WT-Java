package main.java.entities.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.entities.GenericEntity;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Equipment extends GenericEntity<String, Equipment> {
  private String name;
  
  {
    name = null;
  }
  
  @Override
  public Equipment createNewInstance() {
    Equipment obj = new Equipment();
    obj.setId(this.getId());
    return obj;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Equipment\n");
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
    str.append(spaces).append("Class: Equipment\n");
    str.append(super.toString(identSpacesCount));
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
