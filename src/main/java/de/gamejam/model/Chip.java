package de.gamejam.model;

public class Chip {

  private ChipType chipType;
  private ChipColor chipColor;
  private int x;
  private int y;

  public ChipType getChipType() {
    return chipType;
  }

  public void setChipType(ChipType chipType) {
    this.chipType = chipType;
  }

  public ChipColor getChipColor() {
    return chipColor;
  }

  public void setChipColor(ChipColor chipColor) {
    this.chipColor = chipColor;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public String getImageFilename(){
    // Wir haben bestimmte Chips, die keiner Farbe zugeordnet sind und welche, die eine bestimmte Farbe haben
    switch (chipType){
      case SIMPLE:
      case SWITCH:
        return chipColor.getValue() + "_" + chipType.getFilename();
      default:
        return chipType.getFilename();
    }
  }
}
