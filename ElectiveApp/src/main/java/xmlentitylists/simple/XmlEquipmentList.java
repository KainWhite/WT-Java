package xmlentitylists.simple;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import entities.simple.Equipment;
import xmlentitylists.XmlGenericEntityList;

import java.util.List;

@JacksonXmlRootElement(localName = "Equipment")
public class XmlEquipmentList extends XmlGenericEntityList<Equipment> {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "EquipmentItem")
  private List<Equipment> entities;
  
  @Override
  public Class getListType() {
    return Equipment.class;
  }
}