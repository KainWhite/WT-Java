import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entities.GenericEntity;
import exceptions.InvalidXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xmlentitylists.XmlElectiveApp;
import xmlentitylists.XmlGenericEntityList;
import xmlentitylists.complex.XmlElectiveList;
import xmlentitylists.complex.XmlTeacherList;
import xmlentitylists.simple.XmlCircumstanceList;
import xmlentitylists.simple.XmlEquipmentList;
import xmlentitylists.simple.XmlStudentList;
import xmlentitylists.simple.XmlSubjectList;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConverter {
  private static final Logger logger = LogManager.getLogger();
  private static final XmlMapper mapper = new XmlMapper();
  
  static {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
  }
  
  public static void sendXmlElectiveAppToDB(String commonXmlPath,
                                            Connection c) {
    try {
      XmlValidator.validateXmlAgainstXsd(commonXmlPath + ".xml",
                                         commonXmlPath + ".xsd");
      XmlElectiveApp xmlElectiveApp = mapper.readValue(new File(
          commonXmlPath + ".xml"), XmlElectiveApp.class);
      sendCircumstancesToDB(xmlElectiveApp.getCircumstances(), c);
      sendElectivesToDB(xmlElectiveApp.getElectives(), c);
      sendEquipmentToDB(xmlElectiveApp.getEquipment(), c);
      sendStudentsToDB(xmlElectiveApp.getStudents(), c);
      sendSubjectsToDB(xmlElectiveApp.getSubjects(), c);
      sendTeachersToDB(xmlElectiveApp.getTeachers(), c);
    } catch (InvalidXmlException | IOException e) {
      logger.debug(e.getMessage());
    }
    
  }
  
  private static String entityListToString(
      XmlGenericEntityList<? extends GenericEntity> entityList) {
    StringBuilder res = new StringBuilder();
    for (GenericEntity entity : entityList.getEntities()) {
      res.append(entity.getId()).append("\n");
    }
    return res.toString();
  }
  
  private static void sendCircumstancesToDB(XmlCircumstanceList circumstances,
                                            Connection c) {
    String insertQuery = "INSERT INTO circumstances (id, roomNumber, time)\n"
                         + "VALUES (?, ?, ?)\n"
                         + "ON DUPLICATE KEY\n"
                         + "  UPDATE roomNumber = ?, time = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      circumstances.forEach((circumstance) -> {
        try {
          preparedStatement.setString(1, circumstance.getId());
          preparedStatement.setInt(2, circumstance.getRoomNumber());
          // TODO: 17.12.2019 fix those nulls 
          preparedStatement.setDate(3, null);
          preparedStatement.setInt(4, circumstance.getRoomNumber());
          preparedStatement.setDate(5, null);
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  
  private static void sendElectivesToDB(XmlElectiveList electives,
                                        Connection c) {
    String insertQuery =
        "INSERT INTO electives (id, circumstances, equipment, students,"
        + "subject,teacher)\n"
        + "VALUES (?, ?, ?, ?, ?, ?)\n"
        + "ON DUPLICATE KEY\n"
        + "  UPDATE circumstances = ?, equipment = ?, students = ?, subject ="
        + " ?, teacher = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      electives.forEach((elective) -> {
        try {
          preparedStatement.setString(1, elective.getId());
          preparedStatement.setString(2,
                                      entityListToString(elective
                                                             .getCircumstances()));
          preparedStatement.setString(3, entityListToString(elective
                                                                .getEquipment()));
          preparedStatement.setString(4, entityListToString(elective
                                                                .getStudents()));
          preparedStatement.setString(5, elective.getSubject().getId());
          preparedStatement.setString(6, elective.getTeacher().getId());
          preparedStatement.setString(7,
                                      entityListToString(elective
                                                             .getCircumstances()));
          preparedStatement.setString(8, entityListToString(elective
                                                                .getEquipment()));
          preparedStatement.setString(9, entityListToString(elective
                                                                .getStudents()));
          preparedStatement.setString(10, elective.getSubject().getId());
          preparedStatement.setString(11, elective.getTeacher().getId());
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  
  private static void sendEquipmentToDB(XmlEquipmentList equipment,
                                        Connection c) {
    String insertQuery = "INSERT INTO equipment (id, name)\n"
                         + "VALUES (?, ?)\n"
                         + "ON DUPLICATE KEY\n"
                         + "  UPDATE name = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      equipment.forEach((equipmentItem) -> {
        try {
          preparedStatement.setString(1, equipmentItem.getId());
          preparedStatement.setString(2, equipmentItem.getName());
          preparedStatement.setString(3, equipmentItem.getName());
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  
  private static void sendStudentsToDB(XmlStudentList students, Connection c) {
    String insertQuery = "INSERT INTO students (id, name, form)\n"
                         + "VALUES (?, ?, ?)\n"
                         + "ON DUPLICATE KEY\n"
                         + "  UPDATE name = ?, form = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      students.forEach((student) -> {
        try {
          preparedStatement.setString(1, student.getId());
          preparedStatement.setString(2, student.getName());
          preparedStatement.setInt(3, student.getForm());
          preparedStatement.setString(4, student.getName());
          preparedStatement.setInt(5, student.getForm());
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  
  private static void sendSubjectsToDB(XmlSubjectList subjects, Connection c) {
    String insertQuery = "INSERT INTO subjects (id, name)\n"
                         + "VALUES (?, ?)\n"
                         + "ON DUPLICATE KEY\n"
                         + "  UPDATE name = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      subjects.forEach((subject) -> {
        try {
          preparedStatement.setString(1, subject.getId());
          preparedStatement.setString(2, subject.getName());
          preparedStatement.setString(3, subject.getName());
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  
  private static void sendTeachersToDB(XmlTeacherList teachers, Connection c) {
    String insertQuery = "INSERT INTO teachers (id, name, subjects)\n"
                         + "VALUES (?, ?, ?)\n"
                         + "ON DUPLICATE KEY\n"
                         + "  UPDATE name = ?, subjects = ?";
    try (PreparedStatement preparedStatement = c
        .prepareStatement(insertQuery)) {
      teachers.forEach((teacher) -> {
        try {
          preparedStatement.setString(1, teacher.getId());
          preparedStatement.setString(2, teacher.getName());
          preparedStatement.setString(3,
                                      entityListToString(teacher
                                                             .getSubjects()));
          preparedStatement.setString(4, teacher.getName());
          preparedStatement.setString(5,
                                      entityListToString(teacher
                                                             .getSubjects()));
          preparedStatement.executeUpdate();
        } catch (SQLException e) {
          logger.error(e.getMessage());
        }
      });
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
}
