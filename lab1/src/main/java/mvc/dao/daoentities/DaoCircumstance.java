package main.java.mvc.dao.daoentities;

import main.java.entities.Circumstance;
import main.java.entities.GenericEntity;

import java.io.Serializable;
import java.util.Map;

public class DaoCircumstance extends GenericDao<String, Circumstance> {
  public DaoCircumstance() {
    super();
  }

  @Override
  public Circumstance create(String key) {
    Circumstance obj = new Circumstance();
    obj.setId(key);
    return generalCreate(obj);
  }

  @Override
  public Circumstance read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Circumstance)identifiedObjects.get(key);
  }
}
