/**
 * Created by Michael Byrd on 3/31/2017.
 */
public class Column {
    int colValue;
    Square[] sqList = new Square[9];

    public Column(int c) {
        colValue = c;
    }

    public void getSqList() {
//        System.out.println("COL " + colValue);
        for (Square s : sqList) {
//            System.out.println(s.getRowValue() + " " + s.getColValue());
        }
    }
    public int getColValue() {
        return colValue;
    }

    public void addSquare(Square sq){
        sqList[sq.getRowValue()] = sq;
    }

}
