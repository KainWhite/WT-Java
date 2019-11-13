package main.java.mvc.dao.daoentities;

import main.java.entities.simple.Student;

public class DaoStudent extends GenericDao<String, Student> {
  public DaoStudent() {
    super();
  }
  
  @Override
  public Student create(String key) {
    Student obj = new Student();
    obj.setId(key);
    return generalCreate(obj);
  }
  
  @Override
  public Student read(String key) {
    return (Student) databaseMap.get(key);
  }
}
