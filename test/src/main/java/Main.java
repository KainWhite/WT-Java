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
  public static XmlMapper mp;
  
  public static void deserialize(Lol l, String str) {
    try {
      l = mp.readValue(str, l.getClass());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(l);
  }
    
  
  public static void main(String[] args) {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(4);
    a.add(5);
    a.add(6);
    a.add(7);
    a.add(8);
    a.forEach(item -> {
      item = 1;
    });
    System.out.println(a);
  }
}


