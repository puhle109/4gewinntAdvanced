package de.gamejam.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.gamejam.model.ui_element.ChipView;

public class Grid {

    private List<LinkedList<ChipView>> fields;

    public Grid(int countRow, int countCol) {
        fields = new ArrayList<>();

        for (int x = 0; x < countCol; x++) {
            LinkedList<ChipView> col = new LinkedList<>();
            for (int y = 0; y < countRow; y++) {
                col.add(new ChipView());
            }
            fields.add(col);
        }
    }

    public List<LinkedList<ChipView>> getChips() {
        return fields;
    }

    private int getRows() {
        return getChips().size();
    }

    ;

    private int getColumns() {
        return getChips().get(0).size();
    }

    ;


    private ChipView getChipViewAt(int row, int col) {
        List<LinkedList<ChipView>> thefield = getChips();

        if (row < 0 || col < 0) {
            return null;
        }

        if (row < thefield.size()) {
            if (col < thefield.get(row).size()) {

                return thefield.get(row).get(col);

            }
        }
        return null;
    }

    public void addChip(Chip chip, int x) {

        LinkedList<ChipView> col = fields.get(x);

        for (ChipView chipView : col) {
            if (chipView.isFree()) {
                chipView.setChip(chip);
                return;
            }
        }
        throw new IllegalStateException("Die Spalte ist schon voll!");
    }

    //TODO:
    private boolean boardFull() {
        return false;
    }


    private int checkHorizontal(int row, int col, int winSize, ChipColor color) {
        int checkWin = 1;

        for (int k = 0; k < winSize; k++) {
            if (color != getChipViewAt(row + k, col).getColor()) {
                break;
            }
            checkWin++;
        }
        return checkWin;
    }

    private int checkVertical(int row, int col, int winSize, ChipColor color) {
        int checkWin = 1;
        for (int k = 0; k < winSize; k++) {
            if (color != getChipViewAt(row, col + k).getColor()) {
                break;
            }
            checkWin++;
        }
        return checkWin;
    }

    ;

    private int checkDiagonal(int row, int col, int winSize, ChipColor color) {
      int checkWin = 1;

      for (int k = 0; k < winSize; k++) {
        if (color != getChipViewAt(row + k, col + k).getColor()) {
          break;
        }
        checkWin++;
      }

      if (checkWin==winSize) {
        return checkWin;
      } else {
        checkWin = 1;

        for (int k = 0; k < winSize; k++) {
          if (color != getChipViewAt(row + k, col - k).getColor()) {
            break;
          }
          checkWin++;
        }

        return checkWin;
      }

    }

    private Winner getWin(int check, int size, ChipColor color) {
        if (check == size) {
            return color == ChipColor.RED ? Winner.RED : Winner.BLUE;
        } else return Winner.NONE;
    }

    public Winner checkWin() {
        return checkWin(4);
    }

    // -1: draw
    // 0: continue
    // 1: red wins
    // 2: blue wins
    // 3: everybody wins
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
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {

                    //falls der stein farbig ist
                    if (getChipViewAt(i, j).getChip() != null) {

                        color = getChipViewAt(i, j).getColor();

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

}

