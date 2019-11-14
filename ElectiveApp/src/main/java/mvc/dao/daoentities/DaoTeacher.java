package main.java.mvc.dao.daoentities;

import main.java.entities.complex.Teacher;

public class DaoTeacher extends GenericDao<String, Teacher> {
  public DaoTeacher() {
    super();
  }
  
  /**
   * Creates Teacher object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Teacher create(String key) {
    Teacher obj = new Teacher();
    obj.setId(key);
    return generalCreate(obj);
  }
}
