package xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import xmlentitylists.complex.XmlElectiveList;
import xmlentitylists.complex.XmlTeacherList;
import xmlentitylists.simple.XmlCircumstanceList;
import xmlentitylists.simple.XmlEquipmentList;
import xmlentitylists.simple.XmlStudentList;
import xmlentitylists.simple.XmlSubjectList;

public class XmlElectiveApp {
  @JacksonXmlProperty(localName = "Circumstances")
  private XmlCircumstanceList circumstances;
  @JacksonXmlProperty(localName = "Electives")
  private XmlElectiveList electives;
  @JacksonXmlProperty(localName = "Equipment")
  private XmlEquipmentList equipment;
  @JacksonXmlProperty(localName = "Students")
  private XmlStudentList students;
  @JacksonXmlProperty(localName = "Subjects")
  private XmlSubjectList subjects;
  @JacksonXmlProperty(localName = "Teachers")
  private XmlTeacherList teachers;
  
  public void setXmlList(XmlGenericEntityList xmlList) {
    if (xmlList instanceof XmlCircumstanceList) {
      setCircumstances((XmlCircumstanceList) xmlList);
    }
    if (xmlList instanceof XmlElectiveList) {
      setElectives((XmlElectiveList) xmlList);
    }
    if (xmlList instanceof XmlEquipmentList) {
      setEquipment((XmlEquipmentList) xmlList);
    }
    if (xmlList instanceof XmlStudentList) {
      setStudents((XmlStudentList) xmlList);
    }
    if (xmlList instanceof XmlSubjectList) {
      setSubjects((XmlSubjectList) xmlList);
    }
    if (xmlList instanceof XmlTeacherList) {
      setTeachers((XmlTeacherList) xmlList);
    }
  }
  
  public XmlCircumstanceList getCircumstances() {
    return circumstances;
  }
  
  public void setCircumstances(XmlCircumstanceList circumstances) {
    this.circumstances = circumstances;
  }
  
  public XmlElectiveList getElectives() {
    return electives;
  }
  
  public void setElectives(XmlElectiveList electives) {
    this.electives = electives;
  }
  
  public XmlEquipmentList getEquipment() {
    return equipment;
  }
  
  public void setEquipment(XmlEquipmentList equipment) {
    this.equipment = equipment;
  }
  
  public XmlStudentList getStudents() {
    return students;
  }
  
  public void setStudents(XmlStudentList students) {
    this.students = students;
  }
  
  public XmlSubjectList getSubjects() {
    return subjects;
  }
  
  public void setSubjects(XmlSubjectList subjects) {
    this.subjects = subjects;
  }
  
  public XmlTeacherList getTeachers() {
    return teachers;
  }
  
  public void setTeachers(XmlTeacherList teachers) {
    this.teachers = teachers;
  }
}
