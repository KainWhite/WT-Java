package parsers.staxparsers;

import entities.GenericEntity;
import entities.simple.Equipment;
import parsers.XmlParserInterface;
import xmlentitylists.XmlGenericEntityList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlEquipmentStaxParser implements XmlParserInterface<Equipment> {
  @Override
  public List<Equipment> getEntityListFromFile(String path) {
    List<Equipment> equipment = null;
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader reader =
          xmlInputFactory.createXMLEventReader(new FileInputStream(path));
      equipment = new ArrayList<>();
      Equipment equipmentItem = null;
      while (reader.hasNext()) {
        XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isStartElement()) {
          StartElement startElement = nextEvent.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "EquipmentItem":
              equipmentItem = new Equipment();
              readEntity(startElement, equipmentItem, null);
              break;
            case "name":
              nextEvent = reader.nextEvent();
              equipmentItem.setName(nextEvent.asCharacters().getData());
              break;
          }
        }
        if (nextEvent.isEndElement()) {
          EndElement endElement = nextEvent.asEndElement();
          if (endElement.getName().getLocalPart().equals("EquipmentItem")) {
            equipment.add(equipmentItem);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return equipment;
  }
  
  private void readEntity(StartElement startElement,
                          GenericEntity entity,
                          XmlGenericEntityList xmlList) {
    Attribute id = startElement.getAttributeByName(new QName("id"));
    if (id != null) {
      entity.setId(id.getValue());
    }
    if (xmlList != null) {
      xmlList.add(entity);
    }
  }
}
