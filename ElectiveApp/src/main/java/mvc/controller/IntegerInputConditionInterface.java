package main.java.mvc.controller;

/**
 * Interface for checking integer input
 */
public interface IntegerInputConditionInterface {
  /**
   * Checks if integer satisfies conditions
   *
   * @param x integer to check
   * @return true, if satisfies, false otherwise
   */
  boolean isAcceptable(int x);
}
