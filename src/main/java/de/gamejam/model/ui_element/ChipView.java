package de.gamejam.model.ui_element;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ChipView extends BorderPane {

  public static final int IMAGE_SIZE = 50;
  protected ImageView imageView;
  private Chip chip;
  private int row;
  private int col;

  public ChipView(int col, int row) {
    super();
    this.row = row;
    this.col = col;
    imageView = new ImageView();
    imageView.setFitHeight(IMAGE_SIZE);
    imageView.setFitWidth(IMAGE_SIZE);
    this.setBorder(new Border(new BorderStroke(Color.BLACK,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    this.setCenter(imageView);
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
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
    Image image = new Image(getClass().getResource("/img/" + filename).toExternalForm());
    imageView.setImage(image);
  }

  public ChipColor getColor() {
    if (chip == null) {
      return null;
    } else {
      return chip.getChipColor();
    }
  }

  public void setColor(ChipColor color){
    if (this.getChip()!=null){
      this.getChip().setChipColor(color);

      String filename = chip.getImageFilename();
      Image image = new Image(getClass().getResource("/img/" + filename).toExternalForm());
      imageView.setImage(image);
    }

  }

  public boolean isFree() {
    return getChip() == null;
  }
}
