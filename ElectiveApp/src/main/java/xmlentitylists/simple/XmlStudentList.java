package xmlentitylists.simple;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import entities.simple.Student;
import xmlentitylists.XmlGenericEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Students")
public class XmlStudentList extends XmlGenericEntityList<Student> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Student")
  private List<Student> entities;
  
  @Override
  public Class getListType() {
    return Student.class;
  }
}