package de.gamejam.control;

import java.util.LinkedList;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipQueue;
import de.gamejam.model.ChipType;

public class QueueController {

  public static final int QUEUE_SIZE = 4;

  public void initQueue(ChipQueue chipQueue) {
    // Benutzbare Chips auffüllen
    for (int i = 0; i < QUEUE_SIZE; i++) {
      chipQueue.getUsableChips().add(generateNewChip());
    }

    // Nachrückqueue wird aufgefüllt. Hier befinden sich immer
    for (int i = 0; i < 2; i++) {
      chipQueue.getNextChips().add(generateNewChip());
    }
  }

  /**
   * Regelt das Verhalten der ChipQueue, wenn ein Chip genutzt wurde
   *
   * @param chipQueue
   * @param chip
   */
  public void useChip(ChipQueue chipQueue, Chip chip) {

    LinkedList<Chip> usableChips = chipQueue.getUsableChips();
    LinkedList<Chip> nextChips = chipQueue.getNextChips();

    // Der Chip wird "benutzt", heißt aus der nutzbar-Queue entfernt
    usableChips.remove(chip);

    // Zwei Chips aus der reserverQueue rutschen nach
    for (int i = 0; i < 2; i++) {
      usableChips.addFirst(nextChips.removeLast());
    }

    // Ein nutzbarer Chip fällt hinten über und ist weg
    usableChips.removeLast();

    // Zwei neu generierte Chip rutscht nach
    for (int i = 0; i < 2; i++) {
      nextChips.addFirst(generateNewChip());
    }
  }

  /**
   * Neuen Chip generieren
   *
   * @return einen neuen Chip
   */
  private Chip generateNewChip() {
    Chip chip = new Chip();

    ChipType chipType = null;

    //Chiptyp ermitteln (mega unschoen derzeit)
    double r = Math.random();

    if (r<=0.6) {
      chipType = ChipType.SIMPLE;
    } else {
      if (r <= 0.7) {
        chipType = ChipType.BOMB;
      } else if (r <= 0.8) {
        chipType = ChipType.COLOR;
      } else if (r <= 0.9) {
        chipType = ChipType.TABLE_FLIP;
      } else {
        chipType = ChipType.SWITCH;
      }
    }

    chip.setChipType(chipType);

    return chip;
  }
}
