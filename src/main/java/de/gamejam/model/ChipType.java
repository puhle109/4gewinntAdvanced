package de.gamejam.model;

public enum ChipType {
  SIMPLE("simple.png", 0.6),

  //einfacher on-place
  SWITCH("switch.png", 0.05),
  BOMB("bomb.png", 0.05),
  COLOR("color.png", 0.05),
  TABLE_FLIP("table_flip.png", 0.05),

  //komplizierter on-place
  CHAOS_ROW("chaos_row.png", 0.0),
  CHAOS_COL("chaos_col.png", 0.0),

  //static oder surround effekt
  PROTECT("protect.png", 0.00),
  OIL("oil.png", 0.00),
  WALL("wall.png", 0.00),
  TUTOR("tutor.png", 0.00);


  private String filename;
  private double probability;
  ChipType(String filename, double probability) {
    this.filename = filename;
    this.probability = probability;
  }

  public String getFilename() {
    return filename;
  }

  public double getProbability() {
    return probability;
  }
}

