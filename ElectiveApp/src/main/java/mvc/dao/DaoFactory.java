package mvc.dao;

import entities.GenericEntity;
import entities.complex.Elective;
import entities.complex.Teacher;
import entities.simple.Circumstance;
import entities.simple.Equipment;
import entities.simple.Student;
import entities.simple.Subject;
import mvc.dao.daoentities.DaoCircumstance;
import mvc.dao.daoentities.DaoElective;
import mvc.dao.daoentities.DaoEquipment;
import mvc.dao.daoentities.DaoGenericEntity;
import mvc.dao.daoentities.DaoInterface;
import mvc.dao.daoentities.DaoStudent;
import mvc.dao.daoentities.DaoSubject;
import mvc.dao.daoentities.DaoTeacher;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that creates Dao objects
 */
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
  
  /**
   * Gets DaoObject for Tmp class
   *
   * @param Tmp Class for which Dao object is required
   * @return null if there are no Dao class for Tmp, Dao object otherwise
   */
  @Override
  public DaoInterface getDao(Class Tmp) {
    DaoCreatorInterface creator = creators.get(Tmp);
    if (creator == null) {
      System.out.println("Dao object for " + Tmp + " not found.");
      return null;
    }
    return creator.create();
  }
}
