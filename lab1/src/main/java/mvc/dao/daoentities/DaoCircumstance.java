package main.java.mvc.dao.daoentities;

import main.java.entities.Circumstance;

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
    return (Circumstance) databaseMap.get(key);
  }
}
