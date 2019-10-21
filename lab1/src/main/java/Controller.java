package main.java;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Controller {
  {
    model = new Model();
  }
  
  Controller(){}
  
  int processCommand(String command, String[] arguments) {
    switch (command) {
      case "create":
      case "c":
        create(arguments[0], arguments[1],
            Arrays.copyOfRange(arguments, 2, arguments.length));
        return 0;
      case "read":
      case "r":
        read(arguments[0]);
        return 0;
      case "update":
      case "u":
        update(arguments[0], arguments[1]);
        return 0;
      case "delete":
      case "d":
        delete(arguments[0]);
        return 0;
      case "exit":
      case "quit":
      case "q":
        return -1;
      default:
        System.out.printf("Unknown command %s", command);
        return 0;
    }
    
  }
  
  private void create (String className, String objName, String[] parameters) {
    Class Tmp;
    try {
      Tmp = Class.forName("main.resources.".concat(className));
      
    } catch (ClassNotFoundException ex) {
      System.out.printf("Unknown class %s", className);
      return Constants.E_UNKNOWN_CLASS;
    }
    Constructor[] tmpConstructors = Tmp.getDeclaredConstructors();
    int ctorNumber = -1;
    for (int i = 0; i < tmpConstructors.length; i++) {
      if (tmpConstructors[i].getParameterCount() == 0) { // idk how to do it with parameters
        ctorNumber = i;
        break;
      }
    }
    if(ctorNumber < 0) {
      System.out.println("No default constructor for this class");
      return Constants.E_NO_DEF_CONSTRUCTOR;
    }
    return model.create(tmpConstructors[ctorNumber], objName, parameters); // need to convert string[] to object[] properly
  }
  
  private void read(String name) {
    int ret = model.initializeObjects(name);
    if(ret != Constants.SUCCESS) {
      return ret;
    }
    int objCountChooseFrom = model.getObjectCount();
    int chosen = 1;
    if(objCountChooseFrom > 1) {
      Scanner in = new Scanner(System.in);
      try {
        chosen = in.nextInt();
      } catch (InputMismatchException ex) {
        chosen = -1;
      }
    }
    return model.read(chosen);
  }
  
  private void update(String name, String newValue) {
    int ret = model.initializeObjects(name);
    if(ret != Constants.SUCCESS) {
      return ret;
    }
    int objCountChooseFrom = model.getObjectCount();
    int objChosen = 1;
    if(objCountChooseFrom > 1) {
      Scanner in = new Scanner(System.in);
      try {
        objChosen = in.nextInt();
      } catch (InputMismatchException ex) {
        objChosen = -1;
      }
    }
    ret = model.initializeFields(objChosen);
    if(ret != Constants.SUCCESS) {
      return ret;
    }
    int fieldCountChooseFrom = model.getFieldCount();
    int fieldChosen = 1;
    if(objCountChooseFrom > 1) {
      Scanner in = new Scanner(System.in);
      try {
        fieldChosen = in.nextInt();
      } catch (InputMismatchException ex) {
        fieldChosen = -1;
      }
    }
    return model.update(fieldChosen, newValue);
  }
  
  private void delete(String name) {
    
  }
  
  private Class getClass(String str) {
    Scanner checker = new Scanner(str);
    if (checker.hasNextBoolean()) {
      return boolean.class;
    }
    if (checker.hasNextByte()) {
      return byte.class;
    }
    if (checker.hasNextShort()) {
      return short.class;
    }
    if (checker.hasNextInt()) {
      return int.class;
    }
    if (checker.hasNextLong()) {
      return long.class;
    }
    if (checker.hasNextFloat()) {
      return float.class;
    }
    if (checker.hasNextDouble()) {
      return double.class;
    }
    if (str.length() == 1) {
      return char.class;
    }
    return String.class;
  } 
  
  private Model model;
}
