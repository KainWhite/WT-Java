package parsers.staxparsers;

import entities.GenericEntity;
import entities.complex.Elective;
import entities.complex.Teacher;
import entities.simple.Circumstance;
import entities.simple.Equipment;
import entities.simple.Student;
import entities.simple.Subject;
import parsers.XmlParserInterface;
import xmlentitylists.XmlGenericEntityList;
import xmlentitylists.simple.XmlCircumstanceList;
import xmlentitylists.simple.XmlEquipmentList;
import xmlentitylists.simple.XmlStudentList;

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

public class XmlElectivesStaxParser implements
                                    XmlParserInterface<Elective> {
  @Override
  public List<Elective> getEntityListFromFile(String path) {
    List<Elective> electives = null;
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader reader =
          xmlInputFactory.createXMLEventReader(new FileInputStream(path));
      electives = new ArrayList<>();
      Elective elective = null;
      XmlCircumstanceList circumstances = null;
      Circumstance circumstance;
      XmlEquipmentList equipment = null;
      Equipment equipmentItem;
      XmlStudentList students = null;
      Student student;
      Subject subject = null;
      Teacher teacher = null;
      while (reader.hasNext()) {
        XMLEvent nextEvent = reader.nextEvent();
        if (nextEvent.isStartElement()) {
          StartElement startElement = nextEvent.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "Elective":
              elective = new Elective();
              readEntity(startElement, elective, null);
              break;
            case "Circumstances":
              circumstances = new XmlCircumstanceList();
              break;
            case "Circumstance":
              circumstance = new Circumstance();
              readEntity(startElement, circumstance, circumstances);
              break;
            case "Equipment":
              equipment = new XmlEquipmentList();
              break;
            case "EquipmentItem":
              equipmentItem = new Equipment();
              readEntity(startElement, equipmentItem, equipment);
              break;
            case "Students":
              students = new XmlStudentList();
              break;
            case "Student":
              student = new Student();
              readEntity(startElement, student, students);
              break;
            case "Subject":
              subject = new Subject();
              readEntity(startElement, subject, null);
              break;
            case "Teacher":
              teacher = new Teacher();
              readEntity(startElement, teacher, null);
              break;
          }
        } else if (nextEvent.isEndElement()) {
          EndElement endElement = nextEvent.asEndElement();
          if (endElement.getName().getLocalPart().equals("Elective")) {
            elective.setCircumstances(circumstances);
            elective.setEquipment(equipment);
            elective.setStudents(students);
            elective.setSubject(subject);
            elective.setTeacher(teacher);
            electives.add(elective);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return electives;
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
