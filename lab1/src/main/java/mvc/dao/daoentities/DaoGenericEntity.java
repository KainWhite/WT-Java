package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;

import java.io.Serializable;
import java.util.Map;

public class DaoGenericEntity extends GenericDao<String, GenericEntity> {
  public DaoGenericEntity() {
    super();
  }

  @Override
  public GenericEntity create(String key) {
    return new GenericEntity();
  }

  @Override
  public GenericEntity read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return identifiedObjects.get(key);
  }
}
