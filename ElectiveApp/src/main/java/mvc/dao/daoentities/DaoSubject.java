package main.java.mvc.dao.daoentities;

import main.java.entities.simple.Subject;

public class DaoSubject extends GenericDao<String, Subject> {
  public DaoSubject() {
    super();
  }
  
  /**
   * Creates Subject object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Subject create(String key) {
    Subject obj = new Subject();
    obj.setId(key);
    return generalCreate(obj);
  }
}
