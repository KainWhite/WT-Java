package main.java.mvc.dao.daoentities;

import main.java.entities.simple.Circumstance;

public class DaoCircumstance extends GenericDao<String, Circumstance> {
  public DaoCircumstance() {
    super();
  }
  
  /**
   * Creates Circumstance object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Circumstance create(String key) {
    Circumstance obj = new Circumstance();
    obj.setId(key);
    return generalCreate(obj);
  }
}
