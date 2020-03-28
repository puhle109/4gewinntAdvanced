package de.gamejam.model;

import java.util.LinkedList;

public class Field {

  private LinkedList<Chip> chips;

  public Field() {
    chips = new LinkedList<>();
  }

  public LinkedList<Chip> getChips() {
    return chips;
  }

  public void addChip(Chip chip){
    chips.add(chip);
  }
}
