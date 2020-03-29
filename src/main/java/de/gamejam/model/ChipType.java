package de.gamejam.model;

public enum ChipType {
  SIMPLE("simple.png", "Normaler Chip"),

  //einfacher on-place
  SWITCH("switch.png", "Vertauscht die Nachbarchips"),
  BOMB("bomb.png", "Zerstört die benachbarten Chips"),
  LIGHTNING("lightning.png", "Zerstört die unteren beiden Chips"),
  COLOR("color.png", "Färbt den unteren Chip"),
  TABLE_FLIP("table_flip.png", "Tauscht mit dem unteren Chip"),

  //komplizierter on-place
//  CHAOS_ROW("chaos_row.png", ""),
//  CHAOS_COL("chaos_col.png", ""),

  //static oder surround effekt
  PROTECT("protect.png", "Ist geschützt vor allem");
//  OIL("oil.png", ""),
//  WALL("wall.png", ""),
//  TUTOR("tutor.png", "");


  private String filename;
  private String description;

  ChipType(String filename, String description) {
    this.filename = filename;
    this.description = description;
  }

  public String getFilename() {
    return filename;
  }

  public String getDescription() {
    return description;
  }
}

