import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entities.GenericEntity;
import exceptions.InvalidXmlException;
import exceptions.XmlReadException;
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
import java.util.HashMap;
import java.util.Map;

public class ElectiveAppXmlImporter {
  private static final Map<Class, String> commonXmlPaths = new HashMap<>();
  private static final XmlMapper mapper = new XmlMapper();
  private static final Logger logger = LogManager.getLogger();
  
  static {
    commonXmlPaths.put(XmlCircumstanceList.class,
                       "../ElectiveApp/src/main/resources/dbCircumstances"
                       + "/dbCircumstances");
    commonXmlPaths.put(XmlElectiveList.class,
                       "../ElectiveApp/src/main/resources/dbElectives"
                       + "/dbElectives");
    commonXmlPaths.put(XmlEquipmentList.class,
                       "../ElectiveApp/src/main/resources/dbEquipment"
                       + "/dbEquipment");
    commonXmlPaths.put(XmlStudentList.class,
                       "../ElectiveApp/src/main/resources/dbStudents"
                       + "/dbStudents");
    commonXmlPaths.put(XmlSubjectList.class,
                       "../ElectiveApp/src/main/resources/dbSubjects"
                       + "/dbSubjects");
    commonXmlPaths.put(XmlTeacherList.class,
                       "../ElectiveApp/src/main/resources/dbTeachers"
                       + "/dbTeachers");
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
  }
  
  private static <G extends GenericEntity, GX extends XmlGenericEntityList<G>>
  XmlGenericEntityList<G> readXml(String xmlPath, Class<GX> xmlClass)
      throws XmlReadException {
    try {
      return mapper.readValue(new File(xmlPath), xmlClass);
    } catch (IOException e) {
      throw new XmlReadException(e.getMessage());
    }
  }
  
  public static void importElectiveAppXmls() {
    String commonElectiveAppXmlPath =
        "src/main/resources/dbElectiveApp/dbElectiveApp";
    final XmlElectiveApp xmlElectiveApp = new XmlElectiveApp();
    
    commonXmlPaths.forEach((xmlClass, commonXmlPath) -> {
      try {
        XmlValidator.validateXmlAgainstXsd(commonXmlPath + ".xml",
                                           commonXmlPath + ".xsd");
        xmlElectiveApp.setXmlList(readXml(commonXmlPath + ".xml", xmlClass));
      } catch (InvalidXmlException | XmlReadException e) {
        logger.debug(e.getMessage());
      }
    });
    try {
      mapper.writeValue(new File(commonElectiveAppXmlPath + ".xml"),
                        xmlElectiveApp);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
