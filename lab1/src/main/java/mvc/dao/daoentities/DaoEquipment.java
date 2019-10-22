package main.java.mvc.dao.daoentities;

import main.java.entities.Equipment;

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
}
