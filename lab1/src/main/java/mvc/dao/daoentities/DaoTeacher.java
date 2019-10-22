package main.java.mvc.dao.daoentities;

import main.java.entities.Teacher;

public class DaoTeacher extends GenericDao<String, Teacher> {
  public DaoTeacher() {
    super();
  }

  @Override
  public Teacher create(String key) {
    Teacher obj = new Teacher();
    obj.setId(key);
    return generalCreate(obj);
  }
}
