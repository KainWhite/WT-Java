package main.java.xmlentitylists;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import main.java.entities.Equipment;

import java.util.List;

@JacksonXmlRootElement(localName = "Equipment")
public class XmlEquipmentList extends XmlGenericEntityList<Equipment> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "Equipment item")
  private List<Equipment> entities;
}