package main.java.mvc.dao.daoentities;

import main.java.entities.complex.Teacher;

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
  
  @Override
  public Teacher read(String key) {
    return (Teacher) databaseMap.get(key);
  }
}
