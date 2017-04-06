import javax.swing.*;

/**
 * Created by Michael Byrd on 3/31/2017.
 */
public class Square extends JTextField {
    String value;
    JTextField squareField = new JTextField(1);
    int rowValue;
    int colValue;

    public String getValue() {
        return value;
    }

    public int getRowValue() {
        return rowValue;
    }

    public int getColValue() {
        return colValue;
    }

    public Square(int r, int c){
        rowValue = r;
        colValue = c;
        String value = "";
        this.setHorizontalAlignment(JTextField.CENTER);
    }
}
