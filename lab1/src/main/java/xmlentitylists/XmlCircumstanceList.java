package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Circumstance;

import java.util.List;

@JacksonXmlRootElement(localName = "Circumstances")
public class XmlCircumstanceList
    extends XmlGenericEntityList<Circumstance> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Circumstance")
  private List<Circumstance> entities;
}
