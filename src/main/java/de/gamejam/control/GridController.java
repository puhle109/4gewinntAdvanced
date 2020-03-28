package de.gamejam.control;

import de.gamejam.model.*;
import de.gamejam.model.ui_element.ChipView;

import java.util.LinkedList;
import java.util.List;

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
        } else return Winner.NONE;
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
        if (chipView.getChip().getChipType() == ChipType.SIMPLE) {
            return;
        }

        if (chipView.getChip().getChipType() == ChipType.SWITCH) {
            skillSwitch(chipView);

        } else if (chipView.getChip().getChipType() == ChipType.BOMB) {
            skillBomb(chipView);

        }
    }

    private void skillSwitch(ChipView chipView){
        int row = chipView.getRow();
        int col = chipView.getCol();

        ChipView left = getGrid().getChipViewAt(row-1, col);
        ChipView right = getGrid().getChipViewAt(row+1, col);

        if (left!=null&&right!=null){
            Chip tmp = left.getChip();
            left.setChip(right.getChip());
            right.setChip(tmp);
        }

    }

    private void skillBomb(ChipView chipView){
        int row = chipView.getRow();
        int col = chipView.getCol();

        ChipView left = getGrid().getChipViewAt(row-1, col);
        ChipView right = getGrid().getChipViewAt(row+1, col);
        ChipView down = getGrid().getChipViewAt(row, col-1);

        left.setChip(null);
        right.setChip(null);
        down.setChip(null);
    }

}