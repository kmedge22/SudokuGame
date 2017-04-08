import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
        Square[][] boardArray = new Square[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Square testSquare = new Square(i, j);
                boardArray[i][j] = testSquare;
//                System.out.println(testSquare);
//                testSquare.setText(Integer.toString(i) + Integer.toString(j));

//                //Coloring the Background
//                //Box 1
//                if(i <= 2 && 3 <= j && j <= 5){
//                    testSquare.setBackground(Color.green);
//                }
//                //Box 2 and 4
//                if((3 <= i && i <= 5) && !(3 <= j && j <= 5)){
//                    testSquare.setBackground(Color.green);
//                }
//                //Box 7
//                if(6 <= i && 3 <= j && j <= 5){
//                    testSquare.setBackground(Color.green);
//                }



                board.add(testSquare);
            }

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
