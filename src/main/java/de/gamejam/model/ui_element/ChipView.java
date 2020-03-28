package de.gamejam.model.ui_element;

import de.gamejam.model.Chip;

import de.gamejam.model.ChipColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChipView extends ImageView {

  private Chip chip;

  public Chip getChip() {
    return chip;
  }

  public void setChip(Chip chip) {
    this.chip = chip;

    // Wenn der Chip entfernt wurde, soll nur das Bild
    if (chip == null) {
      this.setImage(null);
      return;
    }

    String filename = chip.getChipType().getFilename();

    Image image = new Image("/images/" + filename);
    this.setImage(image);
  }

  public ChipColor getColor(){
    if (chip==null) {
      return null;
    } else {
      return chip.getChipColor();
    }

  }

  public boolean isFree() {
    return getChip() == null;
  }
}
