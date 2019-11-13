package main.java.mvc.dao;

import main.java.mvc.dao.daoentities.DaoInterface;

public interface DaoFactoryInterface {
  public DaoInterface getDao(Class Tmp);
  
  public interface DaoCreatorInterface {
    public DaoInterface create();
  }
}
