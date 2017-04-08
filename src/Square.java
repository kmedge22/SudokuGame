import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Michael Byrd on 3/31/2017.
 */
public class Square extends JTextField {
    String value;

    ArrayList<String> availNumbers = new ArrayList<>();
    ArrayList<String> usedNumbers = new ArrayList<>();



    JTextField squareField = new JTextField(1);
    int rowValue;
    int colValue;
    int boxValue;

    public String getValue() {
        return value;
    }

    public int getRowValue() {
        return rowValue;
    }

    public int getColValue() {
        return colValue;
    }

    @Override
    public String toString() {
        return "Square{" +
                "rowValue=" + rowValue +
                ", colValue=" + colValue +
                '}';
    }

    public Square(int r, int c){
        rowValue = r;
        colValue = c;
        boxValue = getBox(r, c);

        if(boxValue == 1 || boxValue == 3 || boxValue == 5 || boxValue == 7){
            this.setBackground(Color.green);
        }

        this.setText(Integer.toString(boxValue));

        String value = "";
        this.setHorizontalAlignment(JTextField.CENTER);

        for(int i = 0; i < 9; i++){
            availNumbers.add(Integer.toString(i+1));
        }

    }

    public int getBox(int r, int c){
        if(r < 3){
            if(c < 3){
                return 0;
            }
            else if(2 < c && c < 6){
                return 1;
            }
            else{
                return 2;
            }
        }
        else if(2 < r && r < 6){
            if(c < 3){
                return 3;
            }
            else if(2 < c && c < 6){
                return 4;
            }
            else{
                return 5;
            }
        }
        else{
            if(c < 3){
                return 6;
            }
            else if(2 < c && c < 6){
                return 7;
            }
            else{
                return 8;
            }
        }
    }

}
