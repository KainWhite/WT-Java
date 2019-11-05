package main.java;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

abstract class Lol <T> {
  @JacksonXmlProperty(localName = "Lol.a")
  public List<T> a;
  
  {
    a = new ArrayList<>();
  }
  
  public List<T> getA() {
    return a;
  }
  
  public void setA(List<T> a) {
    if(a == null) {
      this.a = new ArrayList<>();
    } else {
      this.a = a;
    }
  }
}

class Kek extends Lol<Integer>{
  @JacksonXmlProperty(localName = "Kek.a")
  public List<Integer> a;
  public List<Integer> ggetA() {
    return a;
  }
}

class Lul extends Lol<Integer>{
  @JacksonXmlProperty(localName = "Lul.a")
  public List<Integer> a;
}
