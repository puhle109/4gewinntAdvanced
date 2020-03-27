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

    // TODO: hier muss nun anhand der Wahrscheinlichkeit ein Tp ermittelt werden
    ChipType chipType = null;

    chip.setChipType(chipType);

    return chip;
  }
}
