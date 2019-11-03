package main.java.mvc.dao;

public interface DaoFactoryInterface {
  public DaoInterface getDao(Class Tmp);
  
  public interface DaoCreatorInterface {
    public DaoInterface create();
  }
}
