package de.gamejam.control;

import java.util.LinkedList;
import java.util.List;

import de.gamejam.model.Chip;
import de.gamejam.model.ChipColor;
import de.gamejam.model.Grid;
import de.gamejam.model.Winner;
import de.gamejam.model.ui_element.ChipView;

public class GridController {

  private final Grid grid;

  public GridController() {
    grid = new Grid(7, 9);
  }

  public Grid getGrid() {
    return grid;
  }

  private boolean boardFull() {
    List<LinkedList<ChipView>> field = grid.getChips();

    for (int i = 0; i < field.size(); i++) {
      for (int j = 0; j < field.get(i).size(); j++) {
        if (field.get(i).get(j).getChip() == null) {
          return false;
        }
      }
    }
    return true;
  }

  private int checkHorizontal(int row, int col, int winSize, ChipColor color) {
    int checkWin = 1;

    for (int k = 1; k < winSize; k++) {

      ChipView chipView = grid.getChipViewAt(row + k, col);

      if (chipView == null || color != chipView.getColor()) {
        break;
      }
      checkWin++;
    }
    return checkWin;
  }

  private int checkVertical(int row, int col, int winSize, ChipColor color) {
    int checkWin = 1;

    for (int k = 1; k < winSize; k++) {
      ChipView chipView = grid.getChipViewAt(row, col + k);
      if (chipView == null || color != chipView.getColor()) {
        break;
      }
      checkWin++;
    }
    return checkWin;
  }

  private int checkDiagonal(int row, int col, int winSize, ChipColor color) {
    int checkWin = 1;

    for (int k = 1; k < winSize; k++) {
      ChipView chipView = grid.getChipViewAt(row + k, col + k);
      if (chipView == null || color != chipView.getColor()) {
        break;
      }
      checkWin++;
    }

    if (checkWin == winSize) {
      return checkWin;
    } else {
      checkWin = 1;
      if (col > winSize) {
        for (int k = 1; k < winSize; k++) {
          ChipView chipView = grid.getChipViewAt(row + k, col - k);
          if (chipView == null || color != chipView.getColor()) {
            break;
          }
          checkWin++;
        }
      }
    }

    return checkWin;
  }

  private Winner getWin(int check, int size, ChipColor color) {
    if (check == size) {
      return color == ChipColor.RED ? Winner.RED : Winner.BLUE;
    } else {
      return Winner.NONE;
    }
  }

  public Winner checkWin() {
    return checkWin(4);
  }

  public Winner checkWin(int winSize) {
    boolean redWins = false;
    boolean blueWins = false;
    ChipColor color = null;
    Winner endWin = Winner.NONE;
    Winner tempWin = Winner.NONE;

    if (boardFull()) {
      return Winner.DRAW;
    } else {
      //das gesamte Feld prüfen
      for (int i = 0; i < grid.getRows(); i++) {
        for (int j = 0; j < grid.getColumns(); j++) {

          //falls der stein farbig ist
          if (grid.getChipViewAt(i, j).getChip() != null) {

            color = grid.getChipViewAt(i, j).getColor();

            //falls jemand gewonnen hat, prüfen wer
            tempWin = getWin(checkHorizontal(i, j, winSize, color), winSize, color);

            if (endWin == Winner.NONE) {
              endWin = tempWin;
            } else if (tempWin != Winner.NONE && tempWin != endWin) {
              return Winner.BOTH;
            }

            //spalte prüfen
            tempWin = getWin(checkVertical(i, j, winSize, color), winSize, color);

            if (endWin == Winner.NONE) {
              endWin = tempWin;
            } else if (tempWin != Winner.NONE && tempWin != endWin) {
              return Winner.BOTH;
            }

            //diagonale prüfen
            tempWin = getWin(checkDiagonal(i, j, winSize, color), winSize, color);

            if (endWin == Winner.NONE) {
              endWin = tempWin;
            } else if (tempWin != Winner.NONE && tempWin != endWin) {
              return Winner.BOTH;
            }
          }
        }
      }
    }

    return endWin;
  }

  public void useSpecial(ChipView chipView) {

    switch (chipView.getChip().getChipType()) {
      case SWITCH:
        skillSwitch(chipView);
        break;
      case BOMB:
        skillBomb(chipView);
        break;
      case LIGHTNING:
        skillLightning(chipView);
        break;
      case COLOR:
        skillColor(chipView);
        break;
      case TABLE_FLIP:
        skillFlip(chipView);
        break;
    }

    // Gravity für alle Spalten ausführen
    for (int i = 0; i < grid.getColumns(); i++) {
      gravity(i);
    }
  }

  private void skillSwitch(ChipView chipView) {
    int row = chipView.getRow();
    int col = chipView.getCol();

    ChipView left = getGrid().getChipViewAt(row - 1, col);
    ChipView right = getGrid().getChipViewAt(row + 1, col);

    if (left != null && right != null
        && left.getChip() != null && left.getChip().isNotProtected()
        && right.getChip() != null && right.getChip().isNotProtected()) {
      Chip tmp = left.getChip();
      left.setChip(right.getChip());
      right.setChip(tmp);
    }
  }

  private void skillBomb(ChipView chipView) {
    int row = chipView.getRow();
    int col = chipView.getCol();

    ChipView left = getGrid().getChipViewAt(row - 1, col);
    ChipView right = getGrid().getChipViewAt(row + 1, col);
    ChipView down = getGrid().getChipViewAt(row, col - 1);
    if (left != null && left.getChip() != null && left.getChip().isNotProtected()) {
      left.setChip(null);
    }
    if (right != null && right.getChip() != null && right.getChip().isNotProtected()) {
      right.setChip(null);
    }
    if (down != null && down.getChip() != null && down.getChip().isNotProtected()) {
      down.setChip(null);
    }
    chipView.setChip(null);
  }

  private void skillLightning(ChipView chipView) {
    int row = chipView.getRow();
    int col = chipView.getCol();

    for (int i = 1; i <= 2; i++) {
      ChipView down = getGrid().getChipViewAt(row, col - i);
      if (down != null && down.getChip() != null && down.getChip().isNotProtected()) {
        down.setChip(null);
      }
    }
  }

  private void skillColor(ChipView chipView) {
    int row = chipView.getRow();
    int col = chipView.getCol();
    ChipColor color = chipView.getColor();

    ChipView down = getGrid().getChipViewAt(row, col - 1);
    if (down != null && down.getChip() != null && down.getChip().isNotProtected()) {
      down.getChip().setChipColor(color);
    }
  }

  private void skillFlip(ChipView chipView) {
    int row = chipView.getRow();
    int col = chipView.getCol();

    ChipView down = getGrid().getChipViewAt(row, col - 1);
    if (down != null && down .getChip() != null && down.getChip().isNotProtected()) {
      Chip tmp = down.getChip();
      down.setChip(chipView.getChip());
      chipView.setChip(tmp);
    }
  }

  /**
   * alle steine die nicht auf einem stein oder dem boden liegen "fallen" herunter, bis alle liegen
   */
  public void gravity(int col) {
    LinkedList<ChipView> chipViews = grid.getChips().get(col);

    for (int i = chipViews.size() - 1; i > 0 ; i--) {
      ChipView chipView = chipViews.get(i);
      ChipView nextChipView = chipViews.get(i - 1);

      if (chipView.getChip() != null && nextChipView.getChip() == null) {
        nextChipView.setChip(chipView.getChip());
        chipView.setChip(null);
      }
    }
  }
}