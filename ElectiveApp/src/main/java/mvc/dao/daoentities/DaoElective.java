package main.java.mvc.dao.daoentities;

import main.java.entities.complex.Elective;

public class DaoElective extends GenericDao<String, Elective> {
  public DaoElective() {
    super();
  }
  
  /**
   * Creates Elective object with id = key and passes it to generalCreate
   *
   * @param key object id
   * @return generalCreate(createdObject)
   */
  @Override
  public Elective create(String key) {
    Elective obj = new Elective();
    obj.setId(key);
    return generalCreate(obj);
  }
}
