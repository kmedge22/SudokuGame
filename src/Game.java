/**
 * Created by kelseyedge on 4/18/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Game extends JFrame {
    private Board aBoard;
    JMenuBar menuBar;
    private JMenu fileMenu;
    private JPanel board;


    public Game() {
        //Create Board
        aBoard = new Board(9,9);

        // Creating the Container
        setTitle("Sudoku");
        setSize(750, 750);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board = new JPanel();
        board.setLayout(new GridLayout(9, 9));

        initializeBoard();

        //initially show available numbers if mouse hovers
        hints(aBoard.getBoard());

        //build
        container.add(board);
        this.add(container);
        buildMenu();
        board.setVisible(true);


    }

    /**
     * Makes an ArrayList of Characters. Uses ArrayList to ensure what a user enters
     * in a square is a valid entry. Sets a Key Event to each Square so each time
     * a value is typed in a Square, it checks validity, then talks to the hints()
     * method to update available numbers list.
     */
    public void initializeBoard(){
        ArrayList<Character> numerals = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            numerals.add(Character.forDigit(i + 1, 10));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Square testSquare = new Square(i, j);
                testSquare.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent keyEvent) {
                        super.keyTyped(keyEvent);
                        char c = keyEvent.getKeyChar();
                        Square sq = (Square) keyEvent.getSource();
                        for (int l = 0; l < 9; l++) {
                            for (int k = 0; k < 9; k++) {
                                if (sq.compareSquare(aBoard.getSquare(l,k))) {
                                    if (aBoard.getSquare(l,k).checkText(Character.toString(c)) ||
                                            !numerals.contains(c) ||
                                            !sq.checkText("")) {
                                        keyEvent.consume();
                                    }
                                }

                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent keyEvent) {
                        super.keyPressed(keyEvent);
                        hints(aBoard.getBoard());
                    }
                });
                aBoard.setSquare(i,j,testSquare);
                buildMenu();
                board.add(testSquare);
            }
        }
    }

    /**
     * Creates an ArrayList to track values that can be used in a Square. Compares this ArrayList to
     * other squares in the same row, column, and box. If they share a common value, that value is
     * removed from the ArrayList. A ToolTipText is set to display the ArrayList of available
     * numbers for each square.
     * @param board Sudoku Board
     */
    public void hints(Square[][] board) {
        ArrayList<String> hintList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Square sq = board[i][j];
                hintList.clear();
                for (int m = 0; m < 9; m++) {
                    hintList.add(Integer.toString(m + 1));
                }
                for (int k = 0; k < 9; k++) {
                    for (int l = 0; l < 9; l++) {
                        if (sq.compareSquare(board[k][l])) {
                            if (hintList.contains(board[k][l].getText())) {
                                hintList.remove(hintList.indexOf(board[k][l].getText()));
                            }
                        }
                        sq.setToolTipText(hintList.toString());
                    }
                }
            }
        }
    }


    /**
     * Builds MenuBar and sets action listeners to each click event of the menu items.
     */
    public void buildMenu() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        JMenuItem menuItem = new JMenuItem("New");
        menuItem.addActionListener(e -> newMenu());
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> open());
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> save());
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> exit());
        fileMenu.add(menuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Exits system
     */
    public void exit(){
        System.exit(0);
    }

    /**
     * Clears the Sudoku Board
     */
    public void newMenu(){
        for(int i = 0; i < 9; i ++){
            for(int j = 0; j < 9; j++){
                aBoard.getSquare(i,j).setText("");
                hints(aBoard.getBoard());
            }
        }
    }

    /**
     * Saves the values of Sudoku Board to a text file row by row.
     */
    public void save(){
        BufferedWriter writer = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(Game.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                writer = new BufferedWriter(new FileWriter(file));
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        if(aBoard.getBoard()[i][j].getText().equals("")){
                            writer.write("0");
                        }
                        writer.write(aBoard.getSquare(i,j).getText());
                        if(j == 8){
                            writer.newLine();
                        }
                        else{
                            writer.write("\t");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Clears any existing values on the Board. Reads in text file created
     * from previously saved Sudoku game. Talks to readInts() method for assistance.
     * Creates new Squares on the board matching the values of the Squares from the
     * opened text file.
     */
    public void open(){
        newMenu();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        int[] lineCoord = readInts(fileChooser.getSelectedFile().getAbsolutePath());

        for(int i = 0; i < lineCoord.length; i++){
            if(lineCoord[i] != 0) {
                aBoard.getSquare(i/9,i%9).setText(Integer.toString(lineCoord[i]));
            }
        }
        hints(aBoard.getBoard());
    }

    /**
     * Helper Method to read in integers from text file.
     * @param filename Name of file to read from
     * @return list of integers
     */
    public int[] readInts(String filename) {
        int[] data;
        try {
            java.util.List<Integer> values = new ArrayList<Integer>();
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextInt()) {
                values.add(scanner.nextInt());
            }
            // Copy them to an array of the exact size.
            data = new int[values.size()];
            Iterator<Integer> it = values.iterator();
            int i = 0;
            while (it.hasNext()) {
                data[i] = it.next();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + filename);
            data = new int[0];
        }
        return data;
    }


    public static void main(String[] args) {
        Game testGame = new Game();
        testGame.setVisible(true);

    }

}





