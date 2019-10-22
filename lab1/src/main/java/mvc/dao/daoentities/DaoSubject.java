package main.java.mvc.dao.daoentities;

import main.java.entities.Subject;

public class DaoSubject extends GenericDao<String, Subject> {
  public DaoSubject() {
    super();
  }

  @Override
  public Subject create(String key) {
    Subject obj = new Subject();
    obj.setId(key);
    return generalCreate(obj);
  }
}
