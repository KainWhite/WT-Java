package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;
import main.java.entities.Subject;

import java.io.Serializable;
import java.util.Map;

public class DaoSubject extends GenericDao<String, Subject> {
  public DaoSubject() {
    super();
  }
  
  @Override
  public Subject create(String key) {
    Subject obj = new Subject();
    obj.setId(key);
    return generalCreate(obj);
  }
  
  @Override
  public Subject read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Subject) identifiedObjects.get(key);
  }
}
