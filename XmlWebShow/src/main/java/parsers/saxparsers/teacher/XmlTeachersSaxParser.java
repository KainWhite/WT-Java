package parsers.saxparsers.teacher;

import entities.complex.Teacher;
import parsers.XmlParserInterface;
import parsers.saxparsers.teacher.XmlTeachersSaxHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class XmlTeachersSaxParser implements XmlParserInterface<Teacher> {
  @Override
  public List<Teacher> getEntityListFromFile(String path) {
    List<Teacher> teachers = null;
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      XmlTeachersSaxHandler teachersSaxHandler = new XmlTeachersSaxHandler();
      saxParser.parse(path, teachersSaxHandler);
      teachers = teachersSaxHandler.getTeachers();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return teachers;
  }
}
