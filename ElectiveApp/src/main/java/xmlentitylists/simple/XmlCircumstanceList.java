package main.java.xmlentitylists.simple;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.simple.Circumstance;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Circumstances")
public class XmlCircumstanceList
    extends XmlGenericEntityList<Circumstance> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Circumstance")
  private List<Circumstance> entities;
  
  @Override
  public Class getListType() {
    return Circumstance.class;
  }
}
