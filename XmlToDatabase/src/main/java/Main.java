import exceptions.DatabaseException;
import exceptions.InvalidXmlException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final String sqlUserName = "dbConverter";
  private static final String sqlUserPassword = "123456";
  private static final List<String> localDBPaths = new ArrayList<>();
  
  {
    localDBPaths
        .add("../ElectiveApp/src/main/resources/dbCircumstance/dbCircumstance");
    localDBPaths.add("../ElectiveApp/src/main/resources/dbElective/dbElective");
    localDBPaths
        .add("../ElectiveApp/src/main/resources/dbEquipment/dbEquipment");
    localDBPaths.add("../ElectiveApp/src/main/resources/dbStudent/dbStudent");
    localDBPaths.add("../ElectiveApp/src/main/resources/dbSubject/dbSubject");
    localDBPaths.add("../ElectiveApp/src/main/resources/dbTeacher/dbTeacher");
  }
  
  public static void main(String[] args) {
    Connection c = null;
    try {
      c = DriverManager.getConnection("jdbc:mysql://localhost/dbElectiveApp?"
                                      + "user=" + sqlUserName + "&password="
                                      + sqlUserPassword);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    String str = ElectiveAppXmlImporter.importElectiveAppXmls();
    try {
      XmlValidator.validateXmlAgainstXsd(str + ".xml", str + ".xsd");
      System.out.println("Validation succeeded!");
    } catch (InvalidXmlException e) {
      System.out.println(e.getMessage());
    } catch (DatabaseException e) {
      System.out.println(e.getMessage());
    }
    if (c != null) {
      try {
        c.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
