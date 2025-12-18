package uk.ac.mmu.architecture.infrastructure.utils;

public enum Colour {
  RED("\u001B[31m"),
  BLUE("\u001B[34m"),
  GREEN("\u001B[32m"),
  YELLOW("\u001B[33m"),
  RESET("\u001B[0m");

  public final String unicode;

  private Colour(String unicode) {
    this.unicode = unicode;
  }
}
