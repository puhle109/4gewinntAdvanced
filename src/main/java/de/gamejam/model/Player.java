package de.gamejam.model;

import de.gamejam.model.ui_element.ChipView;

public class Player {

  private ChipQueue chipQueue;
  private int lifePoint;
  private ChipColor chipColor;
  private ChipView choosenChip;

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

  public ChipView getChoosenChip() {
    return choosenChip;
  }

  public void setChoosenChip(ChipView choosenChip) {
    this.choosenChip = choosenChip;
  }
}
