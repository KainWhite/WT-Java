package main.java;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Controller controller = new Controller();
    boolean loop = true;
    while(loop) {
      String command = in.next();
      String[] parameters = in.nextLine().split(" ");
      if(controller.processCommand(command, parameters) == -1) {
        loop = false;
      }
    }
  }
}
