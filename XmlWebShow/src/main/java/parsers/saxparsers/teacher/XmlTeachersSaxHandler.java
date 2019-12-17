package parsers.saxparsers.teacher;

import entities.complex.Teacher;
import entities.simple.Subject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlentitylists.simple.XmlSubjectList;

import java.util.ArrayList;
import java.util.List;

public class XmlTeachersSaxHandler extends DefaultHandler {
  private String elementValue;
  private List<Teacher> teachers;
  private Teacher teacher;
  
  public List<Teacher> getTeachers() {
    return teachers;
  }
  
  @Override
  public void startDocument() throws SAXException {
    teachers = new ArrayList<>();
    super.startDocument();
  }
  
  @Override
  public void startElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes) throws SAXException {
    switch (qName) {
      case "Teacher":
        teacher = new Teacher();
        teacher.setId(attributes.getValue("id"));
        break;
      case "Subjects":
        teacher.setSubjects(new XmlSubjectList());
        break;
      case "Subject":
        Subject subject = new Subject();
        subject.setId(attributes.getValue("id"));
        teacher.getSubjects().add(subject);
        break;
    }
    super.startElement(uri, localName, qName, attributes);
  }
  
  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    elementValue = new String(ch, start, length);
    super.characters(ch, start, length);
  }
  
  @Override
  public void endElement(String uri, String localName, String qName) throws
                                                                     SAXException {
    switch (qName) {
      case "Teacher":
        teachers.add(teacher);
        break;
      case "name":
        teacher.setName(elementValue);
        break;
    }
    super.endElement(uri, localName, qName);
  }
}
