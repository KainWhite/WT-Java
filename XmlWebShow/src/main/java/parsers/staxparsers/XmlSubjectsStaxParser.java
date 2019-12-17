package parsers.staxparsers;

import entities.GenericEntity;
import entities.simple.Subject;
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

public class XmlSubjectsStaxParser implements XmlParserInterface<Subject> {
  @Override
  public List<Subject> getEntityListFromFile(String path) {
    List<Subject> subjects = null;
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader reader =
          xmlInputFactory.createXMLEventReader(new FileInputStream(path));
      subjects = new ArrayList<>();
      Subject subject = null;
      while (reader.hasNext()) {
        XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isStartElement()) {
          StartElement startElement = nextEvent.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "Subject":
              subject = new Subject();
              readEntity(startElement, subject, null);
              break;
            case "name":
              nextEvent = reader.nextEvent();
              subject.setName(nextEvent.asCharacters().getData());
              break;
          }
        }
        if (nextEvent.isEndElement()) {
          EndElement endElement = nextEvent.asEndElement();
          if (endElement.getName().getLocalPart().equals("Subject")) {
            subjects.add(subject);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return subjects;
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
