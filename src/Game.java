import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by Michael Byrd on 3/31/2017.
 */
public class Game extends JFrame{

    public Game(){
        // Creating the Container
        setTitle("Sudoku");
        setSize(750, 750);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JPanel board = new JPanel();

        board.setLayout(new GridLayout(9, 9));

        Square[] squareArray = new Square[81];
        Column[] columnArray = new Column[9];
        Row[] rowArray = new Row[9];

        for(int i = 0; i < 9; i++){
            columnArray[i] = new Column(i);
        }

        for(int i = 0; i < 9; i++){
            rowArray[i] = new Row(i);
        }



        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Square testSquare = new Square(i, j);

                columnArray[j].addSquare(testSquare);

                testSquare.setText(Integer.toString(i) + Integer.toString(j));

                //Coloring the Background
                //Box 2
                if(i <= 2 && 3 <= j && j <= 5){
                    testSquare.setBackground(Color.green);
                }
                //Box 3 and 5
                if((3 <= i && i <= 5) && !(3 <= j && j <= 5)){
                    testSquare.setBackground(Color.green);
                }
                //Box 8
                if(6 <= i && 3 <= j && j <= 5){
                    testSquare.setBackground(Color.green);
                }



                board.add(testSquare);
            }
        }

        for(int i = 0; i < 9; i++){
            columnArray[i].getSqList();
        }

//        Square testSquare = new Square();
//        board.add(testSquare);



        container.add(board);
        this.add(container);
        board.setVisible(true);

    }

    public static void main(String[] args){

        Game testGame = new Game();

        testGame.setVisible(true);

    }
}
