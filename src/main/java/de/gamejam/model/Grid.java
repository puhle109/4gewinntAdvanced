package de.gamejam.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.gamejam.model.ui_element.ChipView;

public class Grid {

  private List<LinkedList<ChipView>> fields;

  public Grid(int countRow, int countCol) {
    fields = new ArrayList<>();

    for (int x = 0; x < countCol; x++) {
      LinkedList<ChipView> col = new LinkedList<>();
      for (int y = 0; y < countRow; y++) {
        col.add(new ChipView());
      }
      fields.add(col);
    }
  }

  public List<LinkedList<ChipView>> getChips() {
    return fields;
  }

  public void addChip(Chip chip, int x) {

    LinkedList<ChipView> col = fields.get(x);

    for (ChipView chipView : col) {
      if (chipView.isFree()) {
        chipView.setChip(chip);
        return;
      }
    }
    throw new IllegalStateException("Die Spalte ist schon voll!");
  }
}
