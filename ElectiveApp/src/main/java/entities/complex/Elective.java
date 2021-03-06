package entities.complex;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import entities.ComplexEntity;
import entities.GenericEntity;
import entities.simple.Circumstance;
import entities.simple.Equipment;
import entities.simple.Student;
import entities.simple.Subject;
import exceptions.NotFoundException;
import xmlentitylists.XmlGenericEntityList;
import xmlentitylists.simple.XmlCircumstanceList;
import xmlentitylists.simple.XmlEquipmentList;
import xmlentitylists.simple.XmlStudentList;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Elective extends ComplexEntity<String, Elective> {
  @JacksonXmlProperty(localName = "Circumstances")
  private XmlCircumstanceList circumstances;
  @JacksonXmlProperty(localName = "Equipment")
  private XmlEquipmentList equipment;
  @JacksonXmlProperty(localName = "Students")
  private XmlStudentList students;
  @JacksonXmlProperty(localName = "Subject")
  private Subject subject;
  @JacksonXmlProperty(localName = "Teacher")
  private Teacher teacher;
  
  {
    subject = null;
    circumstances = null;
    equipment = null;
    teacher = null;
    students = null;
  }
  
  @Override
  public Elective createNewInstance() {
    Elective obj = new Elective();
    obj.setId(this.getId());
    return obj;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Elective\n");
    str.append(super.toString());
    if (subject != null) {
      str.append(" subject:\n");
      str.append(subject.toString(2));
    }
    if (circumstances != null && circumstances.size() != 0) {
      str.append(" circumstances:\n");
      for (Circumstance circumstance : circumstances.getEntities()) {
        if (circumstance != null) {
          str.append(circumstance.toString(2));
        }
      }
    }
    if (equipment != null && equipment.size() != 0) {
      str.append(" equipment:\n");
      for (Equipment equipmentUnit : equipment.getEntities()) {
        if (equipmentUnit != null) {
          str.append(equipmentUnit.toString(2));
        }
      }
    }
    if (teacher != null) {
      str.append(" teacher:\n");
      str.append(teacher.toString(2));
    }
    if (students != null && students.size() != 0) {
      str.append(" students:\n");
      for (Student student : students.getEntities()) {
        if (student != null) {
          str.append(student.toString(2));
        }
      }
    }
    return str.toString();
  }
  
  @Override
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Elective\n");
    str.append(super.toString(identSpacesCount));
    if (subject != null) {
      
      str.append(spaces).append(" subject:\n");
      str.append(subject.toString(identSpacesCount + 2));
    }
    if (circumstances != null && circumstances.size() != 0) {
      str.append(spaces).append(" circumstances:\n");
      for (Circumstance circumstance : circumstances.getEntities()) {
        if (circumstance != null) {
          str.append(circumstance.toString(identSpacesCount + 2));
        }
      }
    }
    if (equipment != null && equipment.size() != 0) {
      str.append(spaces).append(" equipment:\n");
      for (Equipment equipmentUnit : equipment.getEntities()) {
        if (equipmentUnit != null) {
          str.append(equipmentUnit.toString(identSpacesCount + 2));
        }
      }
    }
    if (teacher != null) {
      str.append(spaces).append(" teacher:\n");
      str.append(teacher.toString(identSpacesCount + 2));
    }
    if (students != null && students.size() != 0) {
      str.append(spaces).append(" students:\n");
      for (Student student : students.getEntities()) {
        if (student != null) {
          str.append(student.toString(identSpacesCount + 2));
        }
      }
    }
    return str.toString();
  }
  
  @Override
  public void resetInternalEntityList(String internalEntityListName)
      throws NotFoundException {
    switch (internalEntityListName) {
      case "circumstances":
        circumstances = new XmlCircumstanceList();
        break;
      case "equipment":
        equipment = new XmlEquipmentList();
        break;
      case "students":
        students = new XmlStudentList();
        break;
      default:
        throw new NotFoundException("No entity list with name"
                                    + internalEntityListName + ".");
    }
  }
  
  @Override
  public XmlGenericEntityList getInternalXmlList(String internalEntityListName)
      throws NotFoundException {
    switch (internalEntityListName) {
      case "circumstances":
        return circumstances;
      case "equipment":
        return equipment;
      case "students":
        return students;
      default:
        throw new NotFoundException("No entity list with name"
                                    + internalEntityListName + ".");
    }
  }
  
  /**
   * @return ArrayList&lt;List&lt;GenericEntity&gt;&gt; with such order:
   * circumstances,
   * equipment, students
   */
  @Override
  public List<List<GenericEntity>> getInternalEntityLists() {
    List<List<GenericEntity>> internalEntityLists = new ArrayList<>();
    internalEntityLists.add((List<GenericEntity>) (List<?>) circumstances
        .getEntities());
    internalEntityLists.add((List<GenericEntity>) (List<?>) equipment
        .getEntities());
    internalEntityLists.add((List<GenericEntity>) (List<?>) students
        .getEntities());
    return internalEntityLists;
  }
  
  /**
   * @return ArrayList&lt;GenericEntity&gt; with such order: subject, teacher
   */
  @Override
  public List<GenericEntity> getInternalEntities() {
    List<GenericEntity> internalEntities = new ArrayList<>();
    internalEntities.add(subject);
    internalEntities.add(teacher);
    return internalEntities;
  }
  
  /**
   * @param internalEntities should be with order: subject, teacher
   */
  @Override
  public void setInternalEntities(List<GenericEntity> internalEntities) {
    subject = (Subject) internalEntities.get(0);
    teacher = (Teacher) internalEntities.get(1);
  }
  
  public Subject getSubject() {
    return subject;
  }
  
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
  
  public XmlCircumstanceList getCircumstances() {
    return circumstances;
  }
  
  public void setCircumstances(XmlCircumstanceList circumstances) {
    this.circumstances = circumstances;
  }
  
  public XmlEquipmentList getEquipment() {
    return equipment;
  }
  
  public void setEquipment(XmlEquipmentList equipment) {
    this.equipment = equipment;
  }
  
  public Teacher getTeacher() {
    return teacher;
  }
  
  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }
  
  public XmlStudentList getStudents() {
    return students;
  }
  
  public void setStudents(XmlStudentList students) {
    this.students = students;
  }
}
