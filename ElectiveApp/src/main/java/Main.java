package main.java;

import main.java.mvc.controller.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    Controller controller = new Controller();
    boolean loop = true;
    while (loop) {
      String[] command = in.nextLine().split(" ");
      loop = controller
          .processCommand(command[0],
                          Arrays.copyOfRange(command, 1, command.length));
    }
  }
}
