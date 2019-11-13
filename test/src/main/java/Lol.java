package main.java;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

class Lol <T> {
  @JacksonXmlProperty(localName = "Lol.a")
  public int a;
  {
    a = 1;
  }
  public int getA() {
    return a;
  }
  
  public void setA(int a) {
    this.a = a;
  }
}

class Kek extends Lol<Integer>{
//  @JacksonXmlProperty(localName = "Kek.a")
//  public int a;
  
  {
    a = 2;
  }
  
  
  
  @Override
  public String toString() {
    return "Kek{" +
           "a=" + a +
           '}';
  }
}

class Lul extends Lol<Integer>{
  @JacksonXmlProperty(localName = "Lul.b")
  private int b;
  
  {
    b = 100;
  }
  
  public int getB() {
    return b;
  }
  
  public void setB(int b) {
    this.b = b;
  }
  
  @Override
  public String toString() {
    return "Lul{" +
           "b=" + b +
           '}';
  }
}
