package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;

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
    return databaseMap.get(key);
  }
}
