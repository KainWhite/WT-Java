package main.java.entities.complex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import main.java.entities.ComplexEntity;
import main.java.entities.GenericEntity;
import main.java.entities.simple.Circumstance;
import main.java.entities.simple.Equipment;
import main.java.entities.simple.Student;
import main.java.entities.simple.Subject;
import main.java.xmlentitylists.simple.XmlCircumstanceList;
import main.java.xmlentitylists.simple.XmlEquipmentList;
import main.java.xmlentitylists.simple.XmlStudentList;

import java.util.ArrayList;
import java.util.List;

public class Elective extends ComplexEntity<String, Elective> {
  @JacksonXmlProperty(localName = "Circumstances")
  private XmlCircumstanceList circumstances;
  @JacksonXmlProperty(localName = "Equipment")
  private XmlEquipmentList equipment;
  @JacksonXmlProperty(localName = "Students")
  private XmlStudentList students;
  private Subject subject;
  private Teacher teacher;
  
  {
    id = null;
    subject = null;
    circumstances = new XmlCircumstanceList();
    equipment = new XmlEquipmentList();
    teacher = null;
    students = new XmlStudentList();
  }
  
  public Elective() {
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
  
  @Override
  public List<GenericEntity> getInternalEntities() {
    List<GenericEntity> internalEntities = new ArrayList<>();
    internalEntities.add(subject);
    internalEntities.add(teacher);
    return internalEntities;
  }
  
  @Override
  public void setInternalEntities(List<GenericEntity> internalEntities) {
    subject = (Subject) internalEntities.get(0);
    teacher = (Teacher) internalEntities.get(1);
  }
}
