package de.gamejam.model;

public class Player {

  private String name;
  private ChipQueue chipQueue;

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
