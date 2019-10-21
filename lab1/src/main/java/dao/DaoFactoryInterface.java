package main.java.dao;

public interface DaoFactoryInterface {
  public DaoInterface getDao(Class cls);
}
