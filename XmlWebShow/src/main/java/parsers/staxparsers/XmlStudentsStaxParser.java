package parsers.staxparsers;

import entities.GenericEntity;
import entities.simple.Student;
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

public class XmlStudentsStaxParser implements XmlParserInterface<Student> {
  @Override
  public List<Student> getEntityListFromFile(String path) {
    List<Student> students = null;
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader reader =
          xmlInputFactory.createXMLEventReader(new FileInputStream(path));
      students = new ArrayList<>();
      Student student = null;
      while (reader.hasNext()) {
        XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isStartElement()) {
          StartElement startElement = nextEvent.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "Student":
              student = new Student();
              readEntity(startElement, student, null);
              break;
            case "name":
              nextEvent = reader.nextEvent();
              student.setName(nextEvent.asCharacters().getData());
              break;
            case "form":
              nextEvent = reader.nextEvent();
              student.setForm(Integer.parseInt(nextEvent.asCharacters()
                                                        .getData()));
              break;
          }
        }
        if (nextEvent.isEndElement()) {
          EndElement endElement = nextEvent.asEndElement();
          if (endElement.getName().getLocalPart().equals("Student")) {
            students.add(student);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return students;
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
