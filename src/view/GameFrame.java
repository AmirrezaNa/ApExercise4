package view;
import controller.GameController;
import controller.MyKeyListener;
import model.Location;
import model.PuzzlePiece;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameFrame extends JFrame {
    GamePanel gamePanel;

    public GameFrame() {
        gamePanel = GamePanel.getInstance();
        this.setSize(gamePanel.getSize());
        this.setLocation(gamePanel.getLocation());
        this.setLayout(null);
        this.add(gamePanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new MyKeyListener());
        this.setVisible(true);
        check();
    }

    ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    ArrayList<Integer> piecesRandomOrder = new ArrayList<>(Arrays.asList(7, 0, 1, 6, 3, 2, 4, 5, 8));

    public void check() {
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (piecesRandomOrder.get(i) == 8) {
                gamePanel.setMissingPiece(i);
            }
            if (!GameController.solvable(gamePanel.missingPiece, piecesRandomOrder)) {
                JOptionPane.showMessageDialog(this, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                GamePanel.gameFinished = true;
            }
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(i + " " + piecesRandomOrder.get(i));
            if (gamePanel.missingPiece != i) {
                puzzlePieces.add(new PuzzlePiece(piecesRandomOrder.get(i) + 1 + ".png", new Location(gamePanel.getHeight() / 3 * (i % 3), gamePanel.getWidth() / 3 * (i / 3))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(gamePanel.getHeight() / 3 * (i % 3), gamePanel.getWidth() / 3 * (i / 3))));
            }
        }
    }
}
