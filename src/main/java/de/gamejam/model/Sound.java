package de.gamejam.model;

public enum Sound {
  NONE(null),
  CLICK("click.wav"),
  COLOR("color.wav"),
  FLIP("flip.wav"),
  LIGHTNING("lightning.wav"),
  PROTECT("protect.wav"),
  SWITCH("switch.wav"),
  BOMB("bomb.wav");

  private String filename;

  Sound(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }
}
