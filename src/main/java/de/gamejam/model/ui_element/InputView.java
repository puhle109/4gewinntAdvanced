package de.gamejam.model.ui_element;

public class InputView extends ChipView {

  private int column;

  public InputView(int column) {
    super(-1,-1);
    this.column = column;
  }

  public int getColumn() {
    return column;
  }
}
