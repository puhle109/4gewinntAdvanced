package de.gamejam.model.ui_element;

import javafx.animation.PathTransition;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class SelectableChipView extends ChipView {

  private boolean isSelected;

  public SelectableChipView() {
    super(-1, -1);
  }

  public void setSelected() {
    animate(0, 10);
    isSelected = true;
  }

  public void setUnselected() {
    if (isSelected) {

      animate(10, 0);
      isSelected = false;
    }
  }

  private void animate(double divStart, double divEnd) {
    PathTransition animation = new PathTransition();
    animation.setCycleCount(1);
    animation.setNode(imageView);
    Line shape = new Line();
    shape.setStartX(imageView.getX() + (IMAGE_SIZE / 2));
    shape.setStartY(imageView.getY() + (IMAGE_SIZE / 2) - divStart);
    shape.setEndX(imageView.getX() + (IMAGE_SIZE / 2));
    shape.setEndY(imageView.getY() + (IMAGE_SIZE / 2) - divEnd);
    animation.setPath(shape);
    animation.setAutoReverse(false);
    animation.setDelay(Duration.millis(100));
    animation.play();
  }
}
