package de.gamejam.model;

public enum ChipType {
  SIMPLE("simple.png"),

  //einfacher on-place
  SWITCH("switch.png"),
  BOMB("bomb.png"),
  COLOR("color.png"),
  TABLE_FLIP("table_flip.png"),

  //komplizierter on-place
  CHAOS_ROW("chaos_row.png"),
  CHAOS_COL("chaos_col.png"),

  //static oder surround effekt
  PROTECT("protect.png"),
  OIL("oil.png"),
  WALL("wall.png"),
  TUTOR("tutor.png");


  private String filename;
  ChipType(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

}

