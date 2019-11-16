package main.java.mvc.dao.daoentities;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import main.java.entities.ComplexEntity;
import main.java.entities.GenericEntity;
import main.java.entities.complex.Elective;
import main.java.entities.complex.Teacher;
import main.java.entities.simple.Circumstance;
import main.java.entities.simple.Equipment;
import main.java.entities.simple.Student;
import main.java.entities.simple.Subject;
import main.java.xmlentitylists.XmlComplexEntityList;
import main.java.xmlentitylists.XmlGenericEntityList;
import main.java.xmlentitylists.complex.XmlElectiveList;
import main.java.xmlentitylists.complex.XmlTeacherList;
import main.java.xmlentitylists.simple.XmlCircumstanceList;
import main.java.xmlentitylists.simple.XmlEquipmentList;
import main.java.xmlentitylists.simple.XmlStudentList;
import main.java.xmlentitylists.simple.XmlSubjectList;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
// TODO: 15.11.2019 wrap all parameters in italic and everything other in 
//  bold in javadoc 
/**
 * Parent class for Dao classes
 *
 * @param <K> <b>id</b> type
 * @param <T> entity class
 */
public abstract class GenericDao<K extends Serializable,
    T extends GenericEntity>
    implements DaoInterface<K, T> {
  protected static Map<Serializable, GenericEntity> databaseMap;
  private static Map<Class, String> databasePaths;
  private static XmlMapper mapper;
  private static Map<Serializable, Set<Serializable>> dependencyMap;
  
  static {
    databaseMap = new HashMap<>();
    databasePaths = new HashMap<>();
    databasePaths.put(Circumstance.class,
                      "src/main/resources/dbCircumstances.xml");
    databasePaths.put(Elective.class, "src/main/resources/dbElectives.xml");
    databasePaths.put(Equipment.class, "src/main/resources/dbEquipment.xml");
    databasePaths.put(Student.class, "src/main/resources/dbStudents.xml");
    databasePaths.put(Subject.class, "src/main/resources/dbSubjects.xml");
    databasePaths.put(Teacher.class, "src/main/resources/dbTeachers.xml");
    mapper = new XmlMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    dependencyMap = new HashMap<>();
    updateLocalDatabase();
  }
  
  /**
   * Adds dependentObjId to dependencies of mainObjId
   *
   * @param mainObjId      <b>id</b> of object, that is in an object with
   *                       <b>id</b> = dependentObjId
   * @param dependentObjId <b>id</b> of object, that contains object with 
   *                       <b>id</b> = mainObjId
   */
  private static void updateDependencyMap(Serializable mainObjId,
                                          Serializable dependentObjId) {
    if (!dependencyMap.containsKey(mainObjId)) {
      dependencyMap.put(mainObjId, new HashSet<>());
    }
    dependencyMap.get(mainObjId).add(dependentObjId);
  }
  
  /**
   * Updates dependencies for entity and, if updateRefs flag is set, sets
   * any object inside entity to object from database
   *
   * @param entity     ComplexEntity needed to be updated
   * @param updateRefs true if it is needed to update references to objects
   *                   inside entity
   */
  private static void updateEntityDependencies(ComplexEntity entity,
                                               boolean updateRefs) {
    List<List<GenericEntity>> internalEntityLists =
        entity.getInternalEntityLists();
    internalEntityLists.forEach(internalEntities -> {
      for (int i = 0; i < internalEntities.size(); i++) {
        if (updateRefs) {
          internalEntities.set(i,
                               databaseMap
                                   .get(internalEntities.get(i).getId()));
        }
        if (internalEntities.get(i) != null) {
          updateDependencyMap(internalEntities.get(i).getId(), entity.getId());
        } else {
          internalEntities.remove(i);
          i--;
        }
      }
    });
    List<GenericEntity> internalEntities = entity.getInternalEntities();
    for (int i = 0; i < internalEntities.size(); i++) {
      if (updateRefs) {
        internalEntities.set(i,
                             databaseMap.get(internalEntities.get(i).getId()));
      }
      if (internalEntities.get(i) != null) {
        updateDependencyMap(internalEntities.get(i).getId(), entity.getId());
      }
    }
    if (updateRefs) {
      // cos i'm working with array of referenced objects
      entity.setInternalEntities(internalEntities);
    }
  }
  
  /**
   * Calls updateEntityDependencies for each entity inside xmlEntities
   *
   * @param xmlEntities XmlComplexEntityList that needs to update its
   *                    entities dependencies
   */
  private static void updateXmlEntityListDependencies(XmlComplexEntityList xmlEntities) {
    List<ComplexEntity> entities = xmlEntities.getEntities();
    entities.forEach(entity -> updateEntityDependencies(entity, true));
  }
  
  /**
   * Reads entities from xml file for Class cls and puts them in databaseMap;
   * if entityList is XmlComplexEntityList it calls
   * updateXmlEntityListDependencies for entityList
   *
   * @param entityList XmlGenericEntityList&lt;T&gt; to read xml into
   * @param cls        Class&lt;T&gt; for which xml file is picked and with
   *                   which it is read
   * @param <T>        extends GenericEntity
   */
  private static <T extends GenericEntity> void updateLocalDatabaseList(
      XmlGenericEntityList<T> entityList,
      Class<T> cls) {
    try {
      entityList = mapper.readValue(new File(databasePaths.get(cls)),
                                    entityList.getClass());
      if (entityList instanceof XmlComplexEntityList) {
        updateXmlEntityListDependencies((XmlComplexEntityList) entityList);
      }
      entityList.forEach((item) -> {
        databaseMap.put(item.getId(), item);
      });
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(" + cls.getSimpleName() + ") in "
          + "GenericDao::updateLocalDatabaseList\n" + ex.getMessage());
    }
  }
  
  /**
   * Resets databaseMap according to remote database (calls
   * updateLocalDatabaseList for each entity class)
   */
  public static void updateLocalDatabase() {
    databaseMap.clear();
    // simple classes
    updateLocalDatabaseList(new XmlCircumstanceList(), Circumstance.class);
    updateLocalDatabaseList(new XmlEquipmentList(), Equipment.class);
    updateLocalDatabaseList(new XmlStudentList(), Student.class);
    updateLocalDatabaseList(new XmlSubjectList(), Subject.class);
    // complex classes(if class1 is field of class2, then u should update 
    // class1 first)
    updateLocalDatabaseList(new XmlTeacherList(), Teacher.class);
    updateLocalDatabaseList(new XmlElectiveList(), Elective.class);
  }
  
  /**
   * Update remote database according to databaseMap
   */
  public static void updateGlobalDatabase() {
    XmlGenericEntityList circumstances = new XmlCircumstanceList();
    XmlGenericEntityList electives = new XmlElectiveList();
    XmlGenericEntityList equipment = new XmlEquipmentList();
    XmlGenericEntityList students = new XmlStudentList();
    XmlGenericEntityList subjects = new XmlSubjectList();
    XmlGenericEntityList teachers = new XmlTeacherList();
    databaseMap.forEach((key, obj) -> {
      switch (obj.getClass().getSimpleName()) {
        case "Circumstance":
          circumstances.add(obj);
          break;
        case "Elective":
          electives.add(obj);
          break;
        case "Equipment":
          equipment.add(obj);
          break;
        case "Student":
          students.add(obj);
          break;
        case "Subject":
          subjects.add(obj);
          break;
        case "Teacher":
          teachers.add(obj);
          break;
        default:
          System.out.printf("Unknown class name in database map: %s\n",
                            obj.getClass().getSimpleName());
      }
    });
    List<XmlGenericEntityList> entityLists = new ArrayList<>();
    entityLists.add(circumstances);
    entityLists.add(electives);
    entityLists.add(equipment);
    entityLists.add(students);
    entityLists.add(subjects);
    entityLists.add(teachers);
    entityLists.forEach((entityList) -> {
      if (entityList.size() > 0) {
        try {
          mapper.writeValue(new File(databasePaths
                                         .get(entityList.get(0).getClass())),
                            entityList);
        } catch (IOException ex) {
          System.out.println("IO exception in mapper.writeValue in "
                             + "GenericDao::updateGlobalDatabase\n" + ex
                                 .getMessage());
        }
      }
    });
  }
  
  /**
   * Called from inheritors, adds obj to database and calls updateGlobalDatabase
   *
   * @param obj obj to add to database
   * @return null if object with such id is already in databaseMap, obj
   * otherwise
   */
  T generalCreate(T obj) {
    if (databaseMap.get(obj.getId()) != null) {
      return null;
    }
    databaseMap.put(obj.getId(), obj);
    updateGlobalDatabase();
    return obj;
  }
  
  // TODO: 04.11.2019 throw exception if no object found(read, update, delete)
  
  /**
   * Gets object of class T with id = key
   *
   * @param key object id
   * @return required object
   */
  @Override
  public T read(K key) {
    return (T) databaseMap.get(key);
  }
  
  /**
   * Replaces object with id = obj.getId() with obj
   *
   * @param obj new object
   */
  @Override
  public void update(T obj) {
    databaseMap.put(obj.getId(), obj);
    if (obj instanceof ComplexEntity) {
      updateEntityDependencies((ComplexEntity) obj, false);
    }
    updateGlobalDatabase();
  }
  
  /**
   * Deletes object with id = obj.getId()
   *
   * @param obj object to delete
   */
  @Override
  public void delete(T obj) {
    databaseMap.remove(obj.getId());
    dependencyMap.get(obj.getId()).forEach(dependentObjId -> {
      updateEntityDependencies((ComplexEntity) databaseMap.get(dependentObjId),
                               true);
    });
    dependencyMap.remove(obj.getId());
    updateGlobalDatabase();
  }
}
