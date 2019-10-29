package main.java.mvc.dao;

import main.java.entities.Circumstance;
import main.java.entities.Elective;
import main.java.entities.Equipment;
import main.java.entities.GenericEntity;
import main.java.entities.Student;
import main.java.entities.Subject;
import main.java.entities.Teacher;
import main.java.mvc.dao.daoentities.DaoCircumstance;
import main.java.mvc.dao.daoentities.DaoElective;
import main.java.mvc.dao.daoentities.DaoEquipment;
import main.java.mvc.dao.daoentities.DaoGenericEntity;
import main.java.mvc.dao.daoentities.DaoStudent;
import main.java.mvc.dao.daoentities.DaoSubject;
import main.java.mvc.dao.daoentities.DaoTeacher;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory implements DaoFactoryInterface {
  private Map<Class, DaoCreatorInterface> creators;
  
  public DaoFactory() {
    creators = new HashMap<>();
    creators.put(Circumstance.class, () -> new DaoCircumstance());
    creators.put(Elective.class, () -> new DaoElective());
    creators.put(Equipment.class, () -> new DaoEquipment());
    creators.put(GenericEntity.class, () -> new DaoGenericEntity());
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
