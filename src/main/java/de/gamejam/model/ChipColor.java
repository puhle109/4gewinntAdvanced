package de.gamejam.model;

public enum ChipColor {
  RED("red"),
  BLUE("blue");

  private String value;

  ChipColor(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
