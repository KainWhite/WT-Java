package xmlentitylists.complex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import entities.complex.Teacher;
import xmlentitylists.XmlComplexEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Teachers")
public class XmlTeacherList extends XmlComplexEntityList<Teacher> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Teacher")
  private List<Teacher> entities;
  
  @Override
  public Class getListType() {
    return Teacher.class;
  }
}