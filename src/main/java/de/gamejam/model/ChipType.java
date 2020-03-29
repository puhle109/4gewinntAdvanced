package de.gamejam.model;

public enum ChipType {
  SIMPLE("simple.png", "Normaler Chip", Sound.CLICK),

  //einfacher on-place
  SWITCH("switch.png", "Vertauscht die Nachbarchips", Sound.SWITCH),
  BOMB("bomb.png", "Zerstört die benachbarten Chips", Sound.BOMB),
  LIGHTNING("lightning.png", "Zerstört die unteren beiden Chips", Sound.LIGHTNING),
  COLOR("color.png", "Färbt den unteren Chip", Sound.COLOR),
  TABLE_FLIP("table_flip.png", "Tauscht mit dem unteren Chip", Sound.FLIP),

  //komplizierter on-place
//  CHAOS_ROW("chaos_row.png", ""),
//  CHAOS_COL("chaos_col.png", ""),

  //static oder surround effekt
  PROTECT("protect.png", "Ist geschützt vor allem", Sound.PROTECT),
  BALLOON("balloon.png", "fliegt", Sound.BALLOON),
  ;
//  OIL("oil.png", ""),
//  WALL("wall.png", ""),
//  TUTOR("tutor.png", "");


  private String filename;
  private String description;
  private Sound sound;

  ChipType(String filename, String description, Sound sound) {
    this.filename = filename;
    this.description = description;
    this.sound = sound;
  }

  public String getFilename() {
    return filename;
  }

  public String getDescription() {
    return description;
  }

  public Sound getSound() {
    return sound;
  }
}

