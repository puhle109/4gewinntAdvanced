package de.gamejam.model;

import java.util.LinkedList;

public class ChipQueue {

  private LinkedList<Chip> usableChips;
  private LinkedList<Chip> nextChips;

  public LinkedList<Chip> getUsableChips() {
    if (usableChips == null) {
      usableChips = new LinkedList<>();
    }
    return usableChips;
  }

  public LinkedList<Chip> getNextChips() {
    if (nextChips == null) {
      nextChips = new LinkedList<>();
    }
    return nextChips;
  }
}
