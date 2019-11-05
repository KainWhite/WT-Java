package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Student;

import java.util.List;

@JacksonXmlRootElement(localName = "Students")
public class XmlStudentList extends XmlGenericEntityList<Student> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Student")
  private List<Student> entities;
  
  @Override
  public Class acquireListType() {
    return Student.class;
  }
}