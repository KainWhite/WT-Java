package main.java.mvc;

public class Controller {
  private Model model;

  {
    model = new Model();
  }

  public Controller(){}

  public boolean processCommand(String command, String[] arguments) {
    switch (command) {
      case "create":
      case "c":
        if(arguments.length < 2) {
          System.out.println(
              "Not enough arguments."
              + "Should be \"create className objId [constructorParameters]\"");
          return true;
        }
        create(arguments[0], arguments[1]);
        return true;
      case "read":
      case "r":
        if(arguments.length < 1) {
          System.out.println("Not enough arguments. Should be \"read objId\"");
          return true;
        }
        read(arguments[0]);
        return true;
      case "update":
      case "u":
        if(arguments.length < 1) {
          System.out.println("Not enough arguments. Should be \"update objId\"");
          return true;
        }
        update(arguments[0]);
        return true;
      case "delete":
      case "d":
        if(arguments.length < 1) {
          System.out.println("Not enough arguments. Should be \"delete objId\"");
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

  private void create (String className, String objId) {
    Class Tmp = null;
    try {
      Tmp = Class.forName("main.java.entities.".concat(className));
    } catch (ClassNotFoundException ex) {
      System.out.printf("Unknown class %s\n", className);
      return;
    }
    model.create(Tmp, objId);
  }

  private void read(String name) {
    
  }

  private void update(String name) {
    
  }

  private void delete(String name) {

  }
}
