package de.gamejam.model.ui_element;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ChipView extends BorderPane {

  public static final int IMAGE_SIZE = 50;
  private Chip chip;

  protected ImageView imageView;

  public ChipView() {
    super();
    imageView = new ImageView();
    imageView.setFitHeight(IMAGE_SIZE);
    imageView.setFitWidth(IMAGE_SIZE);
    this.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    this.setCenter(imageView);
  }

  public Chip getChip() {
    return chip;
  }

  public void setChip(Chip chip) {
    this.chip = chip;

    // Wenn der Chip entfernt wurde, soll nur das Bild
    if (chip == null) {
      imageView.setImage(null);
      return;
    }

    String filename = chip.getImageFilename();
    Image image = new Image(getClass().getResource("/" + filename).toExternalForm());
    imageView.setImage(image);
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


}
