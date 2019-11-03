package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Elective;

import java.util.List;

@JacksonXmlRootElement(localName = "Electives")
public class XmlElectiveList extends XmlGenericEntityList<Elective> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Elective")
  private List<Elective> entities;
}