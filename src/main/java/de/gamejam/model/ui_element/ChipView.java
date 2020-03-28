package de.gamejam.model.ui_element;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ChipView extends ImageView {

  private Chip chip;

  public ChipView() {
    super();
    this.setFitHeight(50);
    this.setFitWidth(50);
  }

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

    String filename = chip.getImageFilename();
//    Image image = new Image(getClass().getResource("/images/" + filename).getFile());
//    Image image = new Image("resource/images/" + filename);
    Image image = new Image(getClass().getResource("/" + filename).toExternalForm());
    this.setImage(image);
  }

  public ChipColor getColor() {
    if (chip == null) {
      return null;
    } else {
      return chip.getChipColor();
    }
  }

  public boolean isFree() {
    return getChip() == null;
  }

  public void setSelected() {
    final PathTransition animation = new PathTransition();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.setNode(this);
    Line shape = new Line();
    shape.setStartX(this.getX());
    shape.setStartY(this.getY());
    shape.setEndX(this.getX());
    shape.setEndY(this.getY() + 10);
    animation.setPath(shape);
    animation.setAutoReverse(true);
    animation.setDelay(Duration.millis(100));
  }
}
