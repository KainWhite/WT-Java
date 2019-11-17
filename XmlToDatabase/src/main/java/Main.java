public class Main {
  public static void main(String[] args) {
    String xmlFileName = "../ElectiveApp/src/main/resources" 
                         + "/dbElectives/dbElectives.xml";
    String xsdFileName = "../ElectiveApp/src/main/resources"
                         + "/dbElectives/dbElectives.xsd";
    System.out.println(XmlValidator.validate(xmlFileName, xsdFileName));
  }
}
