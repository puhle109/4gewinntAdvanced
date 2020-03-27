package de.gamejam.model;

public class Player {

  private String name;
  private ChipQueue chipQueue;
  private int lifePoint;

  public Player(String name) {
    this.name = name;
    lifePoint = 3;
    chipQueue = new ChipQueue();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChipQueue getChipQueue() {
    return chipQueue;
  }

  public void setChipQueue(ChipQueue chipQueue) {
    this.chipQueue = chipQueue;
  }
}
