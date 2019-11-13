package main.java.mvc;

import main.java.entities.GenericEntity;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Controller {
  private Model model;
  
  {
    model = new Model();
  }
  
  // TODO: 03.11.2019 create db listener 
  public Controller() {
  }
  
  private int requestIntegerInput(IntegerInputConditionInterface condition,
                                  String messageAsk,
                                  String messageIncorrect) {
    Scanner in = new Scanner(System.in);
    int requestedInteger = -1;
    boolean wasValidInput = false;
    System.out.println(messageAsk);
    while (!wasValidInput || !condition.isAcceptable(requestedInteger)) {
      String inputInteger = in.nextLine();
      try {
        requestedInteger = Integer.parseInt(inputInteger);
        wasValidInput = true;
      } catch (NumberFormatException ex) {
        wasValidInput = false;
      }
      if (!wasValidInput || !condition.isAcceptable(requestedInteger)) {
        System.out.println(messageIncorrect);
      }
    }
    return requestedInteger;
  }
  
  public boolean processCommand(String command, String[] arguments) {
    switch (command) {
      case "create":
      case "c":
        if (arguments.length < 2) {
          System.out.println("Not enough arguments."
                             + "Should be \"create className objId "
                             + "[constructorParameters]\"");
          return true;
        }
        create(arguments[0], arguments[1]);
        return true;
      case "read":
      case "r":
        if (arguments.length < 1) {
          System.out.println("Not enough arguments. Should be \"read objId\"");
          return true;
        }
        read(arguments[0]);
        return true;
      case "update":
      case "u":
        if (arguments.length < 1) {
          System.out
              .println("Not enough arguments. Should be \"update objId\"");
          return true;
        }
        update(arguments[0]);
        return true;
      case "delete":
      case "d":
        if (arguments.length < 1) {
          System.out
              .println("Not enough arguments. Should be \"delete objId\"");
          return true;
        }
        delete(arguments[0]);
        return true;
      case "exit":
      case "quit":
      case "q":
        return false;
      default:
        System.out.printf("Unknown command %s", command);
        return true;
    }
  }
  
  private void create(String className, String objId) {
    if (className.equals("GenericEntity")) {
      System.out.printf("Unknown class %s\n", className);
      return;
    }
    Class Tmp = null;
    try {
      Tmp = Class.forName("main.java.entities.complex.".concat(className));
    } catch (ClassNotFoundException e) {
      try {
        Tmp = Class.forName("main.java.entities.simple.".concat(className));
      } catch (ClassNotFoundException ex) {
        System.out.printf("Unknown class %s\n", className);
        return;
      }
    }
    model.create(Tmp, objId);
  }
  
  private void read(String objId) {
    model.read(objId);
  }
  
  private void update(String objId) {
    // TODO: 04.11.2019 add exit option in any state 
    int maxFieldNumber = model.getEntityFieldCount(objId);
    if (maxFieldNumber == 0) {
      return;
    }
    Scanner in = new Scanner(System.in);
    int fieldNumber = requestIntegerInput(
        (x) -> (x >= 0) && (x <= maxFieldNumber),
        "Type field number(1 - " + maxFieldNumber + "); 0 - exit:",
        "Incorrect value. Try again:");
    if (fieldNumber == 0) {
      System.out.println("Update aborted.");
      return;
    }
    fieldNumber--;
    Field field = model.getRecentEntityField(fieldNumber);
    if (field.getType() == int.class) {
      int fieldValue = requestIntegerInput((x) -> true,
                                           "Type field value (integer):",
                                           "Incorrect value. Try again:");
      model.updateSimpleField(objId, field, fieldValue);
    } else if (field.getType() == String.class) {
      System.out.println("Type field value (string):");
      String fieldValue = in.nextLine();
      model.updateSimpleField(objId, field, fieldValue);
    } else if (XmlGenericEntityList.class.isAssignableFrom(field.getType())) {
      System.out.println("1. Add entity");
      System.out.println("2. Remove entity");
      System.out.println("3. Remove all entities");
      int actionNumber = requestIntegerInput((x) -> (x >= 1) && (x <= 3),
                                             "Type action number (1 - 3):",
                                             "Incorrect value. Try again:");
      String internalObjId;
      switch (actionNumber) {
        case 1:
          System.out.println(
              "Type created entity id that you want to add(string):");
          internalObjId = in.nextLine();
          model.updateEntityListField(objId,
                                      field,
                                      EntityListOperationEnum.ADD,
                                      internalObjId);
          break;
        case 2:
          // TODO: 04.11.2019 show this list 
          System.out.println("Type entity id that you want to remove(string):");
          internalObjId = in.nextLine();
          model.updateEntityListField(objId,
                                      field,
                                      EntityListOperationEnum.REMOVE,
                                      internalObjId);
          break;
        case 3:
          model.updateEntityListField(objId,
                                      field,
                                      EntityListOperationEnum.CLEAR,
                                      null);
          break;
      }
    } else if (GenericEntity.class.isAssignableFrom(field.getType())) {
      System.out.println("Type field value (object id):");
      String internalObjId = in.nextLine();
      model.updateEntityField(objId, field, internalObjId);
    }
  }
  
  private void delete(String objId) {
    model.delete(objId);
  }
}
