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
    // TODO: 22.10.2019 create dao for Tmp and dao.create(objId)
    DaoInterface daoTmp = daoFactory.getDao(Tmp);
    Object obj = daoTmp.create(objId);
    if(obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }

  void read(int objNumber) {
    
  }

  void update(int fieldNumber, String newValue) {
    
  }

  void delete(String name) {
    
  }
}
