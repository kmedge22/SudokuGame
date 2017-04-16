import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Game extends JFrame {


    JMenuBar menuBar;
    private JMenu fileMenu;
    private EventHandler eh;
    Square[][] boardArray = new Square[9][9];


    public Game() {
        // Creating the Container
        setTitle("Sudoku");
        setSize(750, 750);
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(9, 9));
        eh = new EventHandler();


//        Square[][] boardArray = new Square[9][9];
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
                                if (sq.compareSquare(boardArray[l][k])) {
                                    if (boardArray[l][k].getText().equals(Character.toString(c)) ||
                                            !numerals.contains(c) ||
                                            !sq.getText().equals("")) {
                                        keyEvent.consume();
                                    }
                                }

                            }
                        }
//                        hints(boardArray);
                    }

                    @Override
                    public void keyReleased(KeyEvent keyEvent) {
                        super.keyPressed(keyEvent);
                        hints(boardArray);
                    }
                });
                boardArray[i][j] = testSquare;
                buildMenu();
                board.add(testSquare);
//                pack();
            }
        }

        hints(boardArray);


        container.add(board);
        this.add(container);
        board.setVisible(true);

    }

    private void hints(Square[][] board) {
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

    public static void main(String[] args) {
        Game testGame = new Game();
        testGame.setVisible(true);
    }

    public void buildMenu() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        JMenuItem menuItem = new JMenuItem("New");
        menuItem.addActionListener(eh);
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(eh);
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(eh);
        fileMenu.add(menuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            if (arg0.getActionCommand().equals("Exit")) {
                System.exit(0);
            }

            if (arg0.getActionCommand().equals("New")) {
                for(int i = 0; i < 9; i ++){
                    for(int j = 0; j < 9; j++){
                        boardArray[i][j].setText("");
                        hints(boardArray);
//                        System.out.println(boardArray[i][j]);
                    }
                }
            }

            if (arg0.getActionCommand().equals("Save")) {
                BufferedWriter writer = null;
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(Game.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        writer = new BufferedWriter(new FileWriter(file));
                        for(int i = 0; i < 9; i++){
                            for(int j = 0; j < 9; j++){
                                if(boardArray[i][j].getText().equals("")){
                                    writer.write("0");
                                }
                                writer.write(boardArray[i][j].getText());
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
            if (arg0.getActionCommand().equals("Open")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                int[] lineCoord = readInts(fileChooser.getSelectedFile().getAbsolutePath());

                for(int i = 0; i < lineCoord.length; i++){
                    if(lineCoord[i] != 0) {
                        boardArray[i / 9][i % 9].setText(Integer.toString(lineCoord[i]));
                    }
                }
                hints(boardArray);

            }
        }

    }

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

}




