package main.java.mvc.dao.daoentities;

import main.java.entities.simple.Student;

public class DaoStudent extends GenericDao<String, Student> {
  public DaoStudent() {
    super();
  }
  
  /**
   * Creates Student object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Student create(String key) {
    Student obj = new Student();
    obj.setId(key);
    return generalCreate(obj);
  }
}
