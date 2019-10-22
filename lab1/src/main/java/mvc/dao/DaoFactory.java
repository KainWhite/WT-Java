package main.java.mvc.dao;

import main.java.entities.*;
import main.java.mvc.dao.daoentities.*;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory implements DaoFactoryInterface {
  private Map<Class, DaoCreatorInterface> creators;
  
  public DaoFactory() {
    creators = new HashMap<>();
    creators.put(Circumstance.class, () -> new DaoCircumstance());
    creators.put(Elective.class, () -> new DaoElective());
    creators.put(Equipment.class, () -> new DaoEquipment());
    creators.put(Student.class, () -> new DaoStudent());
    creators.put(Subject.class, () -> new DaoSubject());
    creators.put(Teacher.class, () -> new DaoTeacher());
  }
  
  @Override
  public DaoInterface getDao(Class Tmp) {
    DaoCreatorInterface creator = creators.get(Tmp);
    if (creator == null) {
      System.out.println("Dao object for " + Tmp + " not found.");
    }
    return creator.create();
  }
}
