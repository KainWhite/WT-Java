import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final String sqlUserName = "dbConverter";
  private static final String sqlUserPassword = "123456";
  private static final List<String> localDBPaths = new ArrayList<>();
  private static final Logger logger = LogManager.getLogger();
  
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
    logger.debug("Application started.");
    try {
      ElectiveAppXmlImporter.importElectiveAppXmls();
      logger.debug("ElectiveApp xmls imported.");
      
      Connection c = null;
      c = DriverManager.getConnection("jdbc:mysql://localhost/dbElectiveApp?"
                                      + "user=" + sqlUserName + "&password="
                                      + sqlUserPassword);
      DatabaseConverter.sendXmlElectiveAppToDB(
          "src/main/resources/dbElectiveApp/dbElectiveApp", c);
      if (c != null) {
        c.close();
      }
    } catch (SQLException e) {
      logger.fatal(e.getMessage());
    }
    
    
  }
}
