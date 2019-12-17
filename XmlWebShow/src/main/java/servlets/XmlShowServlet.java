package servlets;

import entities.GenericEntity;
import entities.complex.Elective;
import entities.complex.Teacher;
import entities.simple.Circumstance;
import entities.simple.Equipment;
import entities.simple.Student;
import entities.simple.Subject;
import parsers.XmlParserInterface;
import parsers.domparsers.XmlCircumstancesDomParser;
import parsers.staxparsers.XmlElectivesStaxParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class XmlShowServlet<G extends GenericEntity>
    extends HttpServlet {
  private static final String electiveAppProjectFullPath =
      "C:/Users/KainWhite/Documents/University/WT/ElectiveApp/";
  private static final String electiveAppLocalDBPath = "src/main/resources/";
  private final Map<Class<? extends GenericEntity>, String> databasePaths =
      new HashMap<>();
  private final Map<Class<? extends GenericEntity>, XmlParserCreatorInterface>
      parserCreators = new HashMap<>();
  private final Map<Class<? extends GenericEntity>, String> jspEntityListNames =
      new HashMap<>();
  
  {
    databasePaths.put(Circumstance.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbCircumstances/dbCircumstances.xml");
    databasePaths.put(Elective.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbElectives/dbElectives.xml");
    databasePaths.put(Equipment.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbEquipment/dbEquipment.xml");
    databasePaths.put(Student.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbStudents/dbStudents.xml");
    databasePaths.put(Subject.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbSubjects/dbSubjects.xml");
    databasePaths.put(Teacher.class,
                      electiveAppProjectFullPath + electiveAppLocalDBPath
                      + "dbTeachers/dbTeachers.xml");
    
    parserCreators.put(Circumstance.class,
                       XmlCircumstancesDomParser::new);
    parserCreators.put(Elective.class, XmlElectivesStaxParser::new);
//    parserCreators.put(Equipment.class, XmlEquipmentStaxParser::new);
//    parserCreators.put(Student.class, XmlStudentsStaxParser::new);
//    parserCreators.put(Subject.class, XmlSubjectsStaxParser::new);
//    parserCreators.put(Teacher.class, XmlTeachersSaxParser::new);
    
    jspEntityListNames.put(Circumstance.class, "circumstances");
    jspEntityListNames.put(Elective.class, "electives");
    jspEntityListNames.put(Equipment.class, "equipment");
    jspEntityListNames.put(Student.class, "students");
    jspEntityListNames.put(Subject.class, "subjects");
    jspEntityListNames.put(Teacher.class, "teachers");
  }
  
  public abstract Class<? extends GenericEntity> getInternalClass();
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Class internalClass = getInternalClass();
    XmlParserInterface parser = parserCreators.get(internalClass).create();
    List<? extends GenericEntity> entities = parser.getEntityListFromFile(
        databasePaths.get(internalClass));
    request.setAttribute(jspEntityListNames.get(internalClass), entities);
    request.getRequestDispatcher(
        "/WEB-INF/pages/" + jspEntityListNames.get(internalClass) + ".jsp")
           .forward(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
