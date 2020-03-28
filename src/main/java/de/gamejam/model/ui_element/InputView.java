package de.gamejam.model.ui_element;

import javafx.scene.image.Image;

public class InputView extends ChipView {

  private int column;

  public InputView(int column) {
    super();
    this.column = column;
    setDefaultImage();
  }

  public void setDefaultImage() {
    this.setImage(new Image(getClass().getResource("/empty.png").toExternalForm()));
  }

  public int getColumn() {
    return column;
  }


}
