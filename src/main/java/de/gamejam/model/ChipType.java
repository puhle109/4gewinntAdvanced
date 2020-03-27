package de.gamejam.model;

public enum ChipType {
  SIMPLE("simple.png", 0.6),
  SWITCH("switch.png", 0.05),
  BOMB("bomb.png", 0.05),
  COLOR("color.png", 0.05),
  PROTECT("protect.png", 0.05),
  CHAOS_ROW("chaos_row.png", 0.025),
  CHAOS_COL("chaos_col.png", 0.025),
  OIL("oil.png", 0.05),
  TABLE_FLIP("table_flip.png", 0.05),
  WALL("wall.png", 0.05);

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
