import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.DatabaseException;
import exceptions.InvalidXmlException;
import xmlentitylists.XmlGenericEntityList;

public class DatabaseConverter {
  
  private final XmlMapper mapper = new XmlMapper();
  
  {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
  }
  
  public <GX extends XmlGenericEntityList> void sendXmlToDB(String commonXmlPath,
                                                            Class<GX> xmlClass,
                                                            String tableName) {
    try {
      XmlValidator.validateXmlAgainstXsd(commonXmlPath + ".xml",
                                         commonXmlPath + ".xsd");
    } catch (InvalidXmlException e) {
      System.out.println(e.getMessage());
    } catch (DatabaseException e) {
      System.out.println(e.getMessage());
    }
    
  }
}
