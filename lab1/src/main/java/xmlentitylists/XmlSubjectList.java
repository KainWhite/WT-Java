package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Subject;

import java.util.List;

@JacksonXmlRootElement(localName = "Subjects")
public class XmlSubjectList extends XmlGenericEntityList<Subject> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Subject")
  private List<Subject> entities;
  
  @Override
  public Class acquireListType() {
    return Subject.class;
  }
}