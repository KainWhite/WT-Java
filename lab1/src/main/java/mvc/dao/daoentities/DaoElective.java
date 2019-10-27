package main.java.mvc.dao.daoentities;

import main.java.entities.Elective;
import main.java.entities.GenericEntity;

import java.io.Serializable;
import java.util.Map;

public class DaoElective extends GenericDao<String, Elective> {
  public DaoElective() {
    super();
  }

  @Override
  public Elective create(String key) {
    Elective obj = new Elective();
    obj.setId(key);
    return generalCreate(obj);
  }

  @Override
  public Elective read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Elective)identifiedObjects.get(key);
  }
}
