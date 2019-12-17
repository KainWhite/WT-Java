import exceptions.InvalidXmlException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
  private static final SchemaFactory schemaFactory =
      SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
  
  public static void validateXmlAgainstXsd(String xmlPath, String xsdPath)
      throws InvalidXmlException {
    try {
      schemaFactory.newSchema(new File(xsdPath)).newValidator()
                   .validate(new StreamSource(new File(xmlPath)));
    } catch (SAXException | IOException e) {
      throw new InvalidXmlException(
          "Xml is not appropriate.\n  Reason: " + e.getMessage() + "\n");
    }
  }
}
