package main.java.entities;

public class Elective {
  private int id;
  private Subject subject;
  private Circumstance[] circumstances;
  private Equipment[] equipment;
  private Teacher teacher;
  private Student[] students;

  {
    
  }
  
  Elective() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Circumstance[] getCircumstances() {
    return circumstances;
  }

  public void setCircumstances(Circumstance[] circumstances) {
    this.circumstances = circumstances;
  }

  public Equipment[] getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment[] equipment) {
    this.equipment = equipment;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Student[] getStudents() {
    return students;
  }

  public void setStudents(Student[] students) {
    this.students = students;
  }
}
