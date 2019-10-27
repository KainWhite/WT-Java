package main.java;

class Lol {
  private int a;
  
  Lol(){}

  void bar(Lol obj) {
    System.out.println("Lol::bar");
    if(obj.getClass() != Lol.class) {
      obj.bar(this);
    }
  }
}

class Kek extends Lol {
  void bar(Object obj) {
    System.out.println("Kek::bar");
  }
}
