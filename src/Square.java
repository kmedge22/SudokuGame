import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Square extends JFormattedTextField {
    private final Character value;
    private final ArrayList<String> availNumbers = new ArrayList<>();

    private final int rowValue;
    private final int colValue;
    private final int boxValue;

    public Character getValue() {
        return value;
    }

    public int getRowValue() {
        return rowValue;
    }

    public int getColValue() {
        return colValue;
    }

    public boolean compareSquare(Square other) {
        if (this.rowValue == other.rowValue || this.colValue == other.colValue || this.boxValue == other.boxValue) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Square{" +
                "rowValue=" + rowValue +
                ", colValue=" + colValue +
                '}';
    }


    public Square(int r, int c) {
        rowValue = r;
        colValue = c;
        boxValue = getBox(r, c);
        value = '0';


        if (boxValue == 1 || boxValue == 3 || boxValue == 5 || boxValue == 7) {
            this.setBackground(Color.green);
        }


        this.setHorizontalAlignment(JTextField.CENTER);

        for (int i = 0; i < 9; i++) {
            availNumbers.add(Integer.toString(i + 1));
        }

    }

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


}
