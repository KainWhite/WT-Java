package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;
import main.java.entities.Student;

import java.io.Serializable;
import java.util.Map;

public class DaoStudent extends GenericDao<String, Student> {
  public DaoStudent() {
    super();
  }
  
  @Override
  public Student create(String key) {
    Student obj = new Student();
    obj.setId(key);
    return generalCreate(obj);
  }
  
  @Override
  public Student read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Student) identifiedObjects.get(key);
  }
}
