package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;
import main.java.entities.Teacher;

import java.io.Serializable;
import java.util.Map;

public class DaoTeacher extends GenericDao<String, Teacher> {
  public DaoTeacher() {
    super();
  }

  @Override
  public Teacher create(String key) {
    Teacher obj = new Teacher();
    obj.setId(key);
    return generalCreate(obj);
  }

  @Override
  public Teacher read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Teacher)identifiedObjects.get(key);
  }
}
