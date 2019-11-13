package main.java.xmlentitylists.simple;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.simple.Subject;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Subjects")
public class XmlSubjectList extends XmlGenericEntityList<Subject> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Subject")
  private List<Subject> entities;
  
  @Override
  public Class getListType() {
    return Subject.class;
  }
}