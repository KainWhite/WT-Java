package main.java.xmlentitylists.complex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.complex.Teacher;
import main.java.xmlentitylists.XmlComplexEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Teachers")
public class XmlTeacherList extends XmlComplexEntityList<Teacher> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Teacher")
  private List<Teacher> entities;
  
  @Override
  public Class acquireListType() {
    return Teacher.class;
  }
}