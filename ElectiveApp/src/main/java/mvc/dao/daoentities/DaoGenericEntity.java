package mvc.dao.daoentities;

import entities.GenericEntity;

public class DaoGenericEntity extends GenericDao<String, GenericEntity> {
  public DaoGenericEntity() {
    super();
  }
  
  /**
   * Implementation of create method in DaoInterface
   *
   * @return null
   */
  @Override
  public GenericEntity create(String key) {
    return null;
  }
}
