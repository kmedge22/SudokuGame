/**
 * Created by Michael Byrd on 3/31/2017.
 */
public class Row {
    int rowValue;
    Square[] sqList = new Square[9];

    public Row(int c) {
        rowValue = c;
    }

    public void getSqList() {
        System.out.print(rowValue);
        for (Square s : sqList) {
            System.out.println(s.getRowValue() + s.getColValue());
        }
    }
    public int getColValue() {
        return rowValue;
    }

    public void addSquare(Square sq){
        sqList[sq.getColValue()] = sq;
    }

}
