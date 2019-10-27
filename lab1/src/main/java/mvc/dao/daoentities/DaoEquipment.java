package main.java.mvc.dao.daoentities;

import main.java.entities.Equipment;
import main.java.entities.GenericEntity;

import java.io.Serializable;
import java.util.Map;

public class DaoEquipment extends GenericDao<String, Equipment> {
  public DaoEquipment() {
    super();
  }

  @Override
  public Equipment create(String key) {
    Equipment obj = new Equipment();
    obj.setId(key);
    return generalCreate(obj);
  }

  @Override
  public Equipment read(String key) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if (identifiedObjects == null) {
      return null;
    }
    return (Equipment)identifiedObjects.get(key);
  }
}
