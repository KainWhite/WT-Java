package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import main.java.mvc.Controller;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    Controller controller = new Controller();
    boolean loop = true;
    while(loop) {
      String[] command = in.nextLine().split(" ");
      loop = controller.processCommand(
          command[0],
          Arrays.copyOfRange(command, 1, command.length)
      );
    }
  }
}
