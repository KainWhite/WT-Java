package main.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    XmlMapper mp = new XmlMapper();
    mp.enable(SerializationFeature.INDENT_OUTPUT);
    mp.disable(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL);
    Kek kek = new Kek();
//    List<Integer> x = new ArrayList<>();
//    x.add(10);
//    kek.setA(x);
    String kekOutput = "";
    try {
      kekOutput = mp.writeValueAsString(kek);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println(kekOutput);
    System.out.println(kek.getA());
    //System.out.println(kek.ggetA());
//    String str = "<Kek>\n"
//                 + "  <Kek.a>\n"
//                 + "    <Kek.a>10</Kek.a>"
//                 + "  </Kek.a>\n"
//                 + "</Kek>";
    try {
      kek = mp.readValue(kekOutput, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println(kek.getA());
    //System.out.println(kek.ggetA());
//    try {
//      System.out.println(mp.writeValueAsString(kek));
//    } catch (JsonProcessingException e) {
//      e.printStackTrace();
//    }
//    List<Integer> x = new ArrayList<>();
//    x.add(1000);
//    kek.setA(x);
//    try {
//      System.out.println(mp.writeValueAsString(kek));
//    } catch (JsonProcessingException e) {
//      e.printStackTrace();
//    }
  }
}


