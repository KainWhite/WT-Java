package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Teacher;

import java.util.List;

@JacksonXmlRootElement(localName = "Teachers")
public class XmlTeacherList extends XmlGenericEntityList<Teacher> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Teacher")
  private List<Teacher> entities;
}