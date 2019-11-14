package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;

public class DaoGenericEntity extends GenericDao<String, GenericEntity> {
  public DaoGenericEntity() {
    super();
  }
  
  /**
   * Creates GenericEntity object
   *
   * @return created object
   */
  @Override
  public GenericEntity create(String key) {
    return new GenericEntity();
  }
}
