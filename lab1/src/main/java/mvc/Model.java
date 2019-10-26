package main.java.mvc;

import main.java.mvc.dao.DaoFactory;
import main.java.mvc.dao.DaoInterface;

class Model {
  private View view;
  private DaoFactory daoFactory;
  
  {
    view = new View();
    daoFactory = new DaoFactory();
  }

  Model(){}

  void create(Class Tmp, String objId) {
    DaoInterface daoTmp = daoFactory.getDao(Tmp);
    Object obj = daoTmp.create(objId);
    if(obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }

  void read(String objId) {
    //DaoInterface daoTmp = daoFactory.getDao();
  }

  void update(String objId) {
    
  }

  void delete(String objId) {
    
  }
}
