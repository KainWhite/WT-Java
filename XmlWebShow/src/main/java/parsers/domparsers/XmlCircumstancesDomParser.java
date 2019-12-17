package parsers.domparsers;

import entities.simple.Circumstance;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parsers.XmlParserInterface;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlCircumstancesDomParser
    implements XmlParserInterface<Circumstance> {
  @Override
  public List<Circumstance> getEntityListFromFile(String path) {
    List<Circumstance> circumstances = null;
    try {
      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();
      NodeList nList = doc.getElementsByTagName("Circumstance");
      circumstances = new ArrayList<>(nList.getLength());
      for (int i = 0; i < nList.getLength(); i++) {
        Node node = nList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          Circumstance circumstance = new Circumstance();
          circumstance.setId(element.getAttribute("id"));
          circumstance.setRoomNumber(
              Integer.parseInt(element.getElementsByTagName("roomNumber")
                                      .item(0).getTextContent()));
          // TODO: 17.12.2019 deal with date 
          circumstance.setTime(null);
          circumstances.add(circumstance);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return circumstances;
  }
}
