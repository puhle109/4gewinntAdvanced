package de.gamejam.model;

public class Player {

  private ChipQueue chipQueue;
  private int lifePoint;
  private ChipColor chipColor;

  public Player(ChipColor chipColor) {
    this.chipColor = chipColor;
    lifePoint = 3;
    chipQueue = new ChipQueue();
  }

  public ChipQueue getChipQueue() {
    return chipQueue;
  }

  public void setChipQueue(ChipQueue chipQueue) {
    this.chipQueue = chipQueue;
  }

  public ChipColor getColor() {
    return chipColor;
  }
}
