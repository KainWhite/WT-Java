package main.java.mvc.dao.daoentities;

import main.java.entities.Elective;

public class DaoElective extends GenericDao<String, Elective> {
  public DaoElective() {
    super();
  }
  
  @Override
  public Elective create(String key) {
    Elective obj = new Elective();
    obj.setId(key);
    return generalCreate(obj);
  }
  
  @Override
  public Elective read(String key) {
    return (Elective) databaseMap.get(key);
  }
}
