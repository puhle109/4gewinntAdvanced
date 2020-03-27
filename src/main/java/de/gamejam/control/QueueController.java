package de.gamejam.control;

import java.util.LinkedList;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipQueue;
import de.gamejam.model.Player;

public class QueueController {

  public void useChip(Player player, Chip chip){

    ChipQueue chipQueue = player.getChipQueue();
    LinkedList<Chip> usableChips = chipQueue.getUsableChips();
    usableChips.remove(chip);
    LinkedList<Chip> nextChips = chipQueue.getNextChips();

    usableChips.addFirst(nextChips.removeLast());
    usableChips.addFirst(nextChips.removeLast());
  }

  private Chip generateNewChip(){
    Chip chip = new Chip();



    return chip;
  }
}
