/**
 * Created by kelseyedge on 4/18/17.
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Square extends JFormattedTextField {
    private final Character value;
    private final ArrayList<String> availNumbers = new ArrayList<>();

    private final int rowValue;
    private final int colValue;
    private final int boxValue;

    private Square[] row;
    private Square[] col;
    private Square[] boxes;

    public Square(int r, int c) {
        rowValue = r;
        colValue = c;
        boxValue = getBox(r, c);
        value = '0';

        //assign background color to visually distinguish boxes to the user
        if (boxValue == 1 || boxValue == 3 || boxValue == 5 || boxValue == 7) {
            this.setBackground(Color.green);
        }

        this.setHorizontalAlignment(JTextField.CENTER);

        for (int i = 0; i < 9; i++) {
            availNumbers.add(Integer.toString(i + 1));
        }

    }

    public Square[] getRow() {
        return row;
    }

    public void setRow(Square[] row) {
        this.row = row;
    }

    public Square[] getCol() {
        return col;
    }

    public void setCol(Square[] col) {
        this.col = col;
    }

    public void addSquareToRow(int index, Square aSquare){
        row[index]= aSquare;
    }

    public void addSquareToCol(int index, Square aSquare){
        col[index]= aSquare;
    }

    public void addSquareToBox(int index, Square aSquare){
        boxes[index]= aSquare;
    }

    public Square[] getBoxes() {
        return boxes;
    }

    public void setBoxes(Square[] boxes) {
        this.boxes = boxes;
    }

    public Character getValue() {
        return value;
    }

    public int getRowValue() {
        return rowValue;
    }

    public int getColValue() {
        return colValue;
    }

    /**
     * Compares two squares to identify whether they are in the corresponding row, column, or box.
     * @param other Square to be compared
     * @return boolean true or false
     */
    public boolean compareSquare(Square other) {
        if (this.rowValue == other.rowValue || this.colValue == other.colValue || this.boxValue == other.boxValue) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Computes which box a Square belongs to based upon it's row and column value
     * @param r Square's row value
     * @param c Square's column value
     * @return box number as int
     */
    private int getBox(int r, int c) {
        if (r < 3) {
            if (c < 3) {
                return 0;
            } else if (2 < c && c < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (2 < r && r < 6) {
            if (c < 3) {
                return 3;
            } else if (2 < c && c < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (c < 3) {
                return 6;
            } else if (2 < c && c < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }

    @Override
    public String toString() {
        return "Square{" +
                "rowValue=" + rowValue +
                ", colValue=" + colValue +
                '}';
    }


}

