package xmlentitylists.complex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import entities.complex.Elective;
import xmlentitylists.XmlComplexEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Electives")
public class XmlElectiveList extends XmlComplexEntityList<Elective> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Elective")
  private List<Elective> entities;
  
  @Override
  public Class getListType() {
    return Elective.class;
  }
}