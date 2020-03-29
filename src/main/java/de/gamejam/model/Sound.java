package de.gamejam.model;

public enum Sound {
  NONE(null),
  CLICK("click.mp3"),
  COLOR("color.mp3"),
  FLIP("flip.mp3"),
  LIGHTNING("lightning.mp3"),
  PROTECT("protect.mp3"),
  SWITCH("switch.mp3"),
  BOMB("bomb.mp3");

  private String filename;

  Sound(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }
}
