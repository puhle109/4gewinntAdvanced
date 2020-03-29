package de.gamejam.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.gamejam.model.ui_element.ChipView;

public class Grid {

    private List<LinkedList<ChipView>> fields;

    public Grid(int countRow, int countCol) {
        init(countRow, countCol);
    }

    public void init(int countRow, int countCol) {
        fields = new ArrayList<>();
        for (int x = 0; x < countCol; x++) {
            LinkedList<ChipView> col = new LinkedList<>();
            for (int y = 0; y < countRow; y++) {
                col.add(new ChipView(y, x));
            }
            fields.add(col);
        }
    }

    public List<LinkedList<ChipView>> getChips() {
        return fields;
    }

    public int getRowSize() {
        return getChips().size();
    }

    public int getColumnSize() {
        return getChips().get(0).size();
    }

    public int getRowCount() {
        return getChips().get(0).size();
    }

    public int getColumnCount() {
        return getChips().size();
    }

    public ChipView getChipViewAt(int row, int col) {
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

    public boolean isFull(int x) {
        LinkedList<ChipView> col = fields.get(x);
        for (ChipView chipView : col) {
            if (chipView.getChip() == null) return false;
        }
        return true;
    }

    public ChipView addChip(Chip chip, int x) {

        LinkedList<ChipView> col = fields.get(x);


        //falls es ein ballon ist
        if (chip.getChipType() == ChipType.BALLOON) {

            for (int i = col.size() - 1; i >= 0; i--) {
                ChipView chipView = col.get(i);
                if (chipView.isFree()) {
                    chipView.setChip(chip);
                    return chipView;
                }
            }

            //falls es kein ballon ist
        } else {

            //falls oben ein chip ist und spalte nicht leer, gibt es einen ballon
            if (col.getLast().getChip() != null && !isFull(x)) {
                boolean balloonReached = false;

                for (ChipView chipView : col) {

                    //wenn wir ganz oben sind
                    if (col.getLast().getCol() == chipView.getCol()) {
                        chipView.setChip(chip);
                        return chipView;
                    } else {
                        ChipView nextChipView = col.get(chipView.getCol() + 1);

                        if (nextChipView.getChip()!=null
                                && nextChipView.getChip().getChipType() == ChipType.BALLOON) {
                            balloonReached = true;
                        }
                        if (balloonReached) {
                            chipView.setChip(nextChipView.getChip());

                        }
                    }
                }
            } else {
                for (ChipView chipView : col) {
                    if (chipView.isFree()) {
                        chipView.setChip(chip);
                        return chipView;
                    }
                }
            }
        }
        throw new IllegalStateException("Die Spalte ist schon voll!");
    }

}

