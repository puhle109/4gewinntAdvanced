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

  public static final int IMAGE_SIZE = 50;
  private Chip chip;
  private PathTransition animation;

  public ChipView() {
    super();
    this.setFitHeight(IMAGE_SIZE);
    this.setFitWidth(IMAGE_SIZE);
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
    animation = new PathTransition();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.setNode(this);
    Line shape = new Line();
    shape.setStartX(this.getX() + (IMAGE_SIZE/2));
    shape.setStartY(this.getY() + (IMAGE_SIZE/2));
    shape.setEndX(this.getX() + (IMAGE_SIZE/2));
    shape.setEndY(this.getY() + (IMAGE_SIZE/2) - 10);
    animation.setPath(shape);
    animation.setAutoReverse(true);
    animation.setDelay(Duration.millis(100));
    animation.play();
  }

  public void setUnselected(){
    animation.stop();
  }
}
