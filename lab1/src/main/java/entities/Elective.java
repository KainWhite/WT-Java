package main.java.entities;

import main.java.xmlentitylists.XmlCircumstanceList;
import main.java.xmlentitylists.XmlEquipmentList;
import main.java.xmlentitylists.XmlStudentList;

public class Elective extends GenericEntity<String, Elective> {
  private Subject subject;
  private XmlCircumstanceList circumstances;
  private XmlEquipmentList equipment;
  private Teacher teacher;
  private XmlStudentList students;
  
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
      str.append(subject.toString(1));
    }
    if (circumstances != null && circumstances.size() != 0) {
      str.append(" circumstances:\n");
      for (Circumstance circumstance : circumstances.getEntities()) {
        if (circumstance != null) {
          str.append(circumstance.toString(1));
        }
      }
    }
    if (equipment != null && equipment.size() != 0) {
      str.append(" equipment:\n");
      for (Equipment equipmentUnit : equipment.getEntities()) {
        if (equipmentUnit != null) {
          str.append(equipmentUnit.toString(1));
        }
      }
    }
    if (teacher != null) {
      str.append(" teacher:\n");
      str.append(teacher.toString(1));
    }
    if (students != null && students.size() != 0) {
      str.append(" students:\n");
      for (Student student : students.getEntities()) {
        if (student != null) {
          str.append(student.toString(1));
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
      str.append(subject.toString(identSpacesCount + 1));
    }
    if (circumstances != null && circumstances.size() != 0) {
      str.append(spaces).append(" circumstances:\n");
      for (Circumstance circumstance : circumstances.getEntities()) {
        if (circumstance != null) {
          str.append(circumstance.toString(identSpacesCount + 1));
        }
      }
    }
    if (equipment != null && equipment.size() != 0) {
      str.append(spaces).append(" equipment:\n");
      for (Equipment equipmentUnit : equipment.getEntities()) {
        if (equipmentUnit != null) {
          str.append(equipmentUnit.toString(identSpacesCount + 1));
        }
      }
    }
    if (teacher != null) {
      str.append(spaces).append(" teacher:\n");
      str.append(teacher.toString(identSpacesCount + 1));
    }
    if (students != null && students.size() != 0) {
      str.append(spaces).append(" students:\n");
      for (Student student : students.getEntities()) {
        if (student != null) {
          str.append(student.toString(identSpacesCount + 1));
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
}
