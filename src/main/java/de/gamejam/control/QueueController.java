package de.gamejam.control;

import java.util.LinkedList;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.ChipQueue;
import de.gamejam.model.ChipType;

public class QueueController {

  public static final int QUEUE_SIZE = 4;

  public void initQueue(ChipQueue chipQueue, ChipColor color) {
    // Benutzbare Chips auffüllen
    for (int i = 0; i < QUEUE_SIZE; i++) {
      chipQueue.getUsableChips().add(generateNewChip(color));
    }

    // Nachrückqueue wird aufgefüllt. Hier befinden sich immer
    for (int i = 0; i < 2; i++) {
      chipQueue.getNextChips().add(generateNewChip(color));
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
      nextChips.addFirst(generateNewChip(chip.getChipColor()));
    }
  }

  /**
   * Neuen Chip generieren
   *
   * @return einen neuen Chip
   */
  private Chip generateNewChip(ChipColor color) {
    Chip chip = new Chip();
    chip.setChipColor(color);

    double r = Math.random();

    ChipType chipType;
    if (r <= 0.6) {
      chipType = ChipType.SIMPLE;
    } else if (r <= 0.65) {
      chipType = ChipType.BOMB;
    } else if (r <= 0.7) {
      chipType = ChipType.LIGHTNING;
    } else if (r <= 0.75) {
      chipType = ChipType.TABLE_FLIP;
    } else if (r <= 0.85) {
      chipType = ChipType.PROTECT;
    } else {
      chipType = ChipType.SWITCH;
    }

    chip.setChipType(chipType);

    return chip;
  }
}
