package main.java.mvc;

import main.java.entities.GenericEntity;
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
    GenericEntity obj = daoTmp.create(objId);
    // TODO: 27.10.2019 post create message in view
    if (obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }

  void read(String objId) {
    Class genericEntityClass;
    try {
      genericEntityClass = Class.forName("main.java.entities.".concat("GenericEntity"));
    } catch (ClassNotFoundException ex) {
      System.out.printf("Unknown class %s\n", "GenericEntity");
      return;
    }
    DaoInterface daoGenericEntity = daoFactory.getDao(genericEntityClass);
    GenericEntity obj = daoGenericEntity.read(objId);
    if(obj == null) {
      System.out.println("No such object");
    } else {
      view.displayObject(obj);
    }
  }

  void update(String objId) {
    
  }

  void delete(String objId) {
    
  }
}
