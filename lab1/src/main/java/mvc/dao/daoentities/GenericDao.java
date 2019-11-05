package main.java.mvc.dao.daoentities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import main.java.entities.Circumstance;
import main.java.entities.Elective;
import main.java.entities.Equipment;
import main.java.entities.GenericEntity;
import main.java.entities.Student;
import main.java.entities.Subject;
import main.java.entities.Teacher;
import main.java.mvc.dao.DaoInterface;
import main.java.xmlentitylists.XmlCircumstanceList;
import main.java.xmlentitylists.XmlElectiveList;
import main.java.xmlentitylists.XmlEquipmentList;
import main.java.xmlentitylists.XmlGenericEntityList;
import main.java.xmlentitylists.XmlStudentList;
import main.java.xmlentitylists.XmlSubjectList;
import main.java.xmlentitylists.XmlTeacherList;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDao<K extends Serializable,
    T extends GenericEntity>
    implements DaoInterface<K, T> {
  protected static Map<Serializable, GenericEntity> databaseMap;
  private static Map<Class, String> databasePaths;
  private static XmlMapper mapper;
  
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
    updateLocalDatabase();
  }
  
  public static void updateLocalDatabase() {
//    databasePaths.forEach((cls, path) -> {
//      XmlMapper mapper = new XmlMapper();
//      //cls.getClass();
//      List<cls> l;
//      try {
//        mapper.readValue(new File(path),
//                         new TypeReference<cls>() {});
//      } catch (IOException ex) {
//        System.out.println(
//            "IO exception in mapper.readValue in 
//            GenericDao::readMapFromXML\n" + ex.getMessage());
//      }
//    });
    // TODO: 03.11.2019 REPLACE THESE LOTS OF SHIT WITH SOMETHING LIKE ABOVE
    // TODO: 05.11.2019 when reading database make sure,
    //  that (entity1 -> entity2) and entity2 are the same object 
    databaseMap.clear();
    
    XmlCircumstanceList circumstances = new XmlCircumstanceList();
    try {
      circumstances = mapper.readValue(
          new File(databasePaths.get(Circumstance.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Circumstance) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    circumstances.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
    
    XmlElectiveList electives = new XmlElectiveList();
    try {
      electives = mapper.readValue(
          new File(databasePaths.get(Elective.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Elective) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    electives.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
    
    XmlEquipmentList equipment = new XmlEquipmentList();
    try {
      equipment = mapper.readValue(
          new File(databasePaths.get(Equipment.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Equipment) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    equipment.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
    
    XmlStudentList students = new XmlStudentList();
    try {
      students = mapper.readValue(
          new File(databasePaths.get(Student.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Student) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    students.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
    
    XmlSubjectList subjects = new XmlSubjectList();
    try {
      subjects = mapper.readValue(
          new File(databasePaths.get(Subject.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Subject) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    subjects.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
    
    XmlTeacherList teachers = new XmlTeacherList();
    try {
      teachers = mapper.readValue(
          new File(databasePaths.get(Teacher.class)),
          new TypeReference<>() {});
    } catch (IOException ex) {
      System.out.println(
          "IO exception in mapper.readValue(Teacher) in "
          + "GenericDao::updateLocalDatabase\n"
          + ex.getMessage());
    }
    teachers.forEach((item) -> {
      databaseMap.put(item.getId(), item);
    });
  }
  
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
          mapper.writeValue(
              new File(databasePaths.get(entityList.get(0).getClass())),
              entityList);
        } catch (IOException ex) {
          System.out.println(
              "IO exception in mapper.writeValue in "
              + "GenericDao::updateGlobalDatabase\n"
              + ex.getMessage());
        }
      }
    });
//    try {
//      mapper.writeValue(
//          new File("src/main/resources/dbLol.xml"),
//          new Student());
//    } catch (IOException ex) {
//      System.out.println(
//          "IO exception in mapper.writeValue in "
//          + "GenericDao::updateGlobalDatabase\n"
//          + ex.getMessage());
//    }
  }
  
  // create, that implemented in inheritors, calls generalCreate(new T())
  T generalCreate(T obj) {
    if (databaseMap.get(obj.getId()) != null) {
      return null;
    }
    databaseMap.put(obj.getId(), obj);
    updateGlobalDatabase();
    return obj;
  }
  
  // TODO: 04.11.2019 throw exception if no object found(read, update, delete)
  // read implemented in inheritors
  
  @Override
  public void update(T obj) {
    databaseMap.put(obj.getId(), obj);
    updateGlobalDatabase();
  }
  
  @Override
  public void delete(T obj) {
  }
}
