package de.gamejam.control;

import de.gamejam.model.Grid;

public class GridController {

  private final Grid grid;

  public GridController() {
    grid = new Grid(7,9);
  }

  public Grid getGrid() {
    return grid;
  }
}
