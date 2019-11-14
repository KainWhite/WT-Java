package main.java.mvc.dao.daoentities;

import main.java.entities.simple.Equipment;

public class DaoEquipment extends GenericDao<String, Equipment> {
  public DaoEquipment() {
    super();
  }
  
  /**
   * Creates Equipment object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Equipment create(String key) {
    Equipment obj = new Equipment();
    obj.setId(key);
    return generalCreate(obj);
  }
}
