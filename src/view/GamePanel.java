package view;

import model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    public static GamePanel panelInstance;
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public int missingPiece = 0;
    public String gameState = "#";

    static boolean gameFinished = false;
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    int maxSize = Math.max(screenWidth, screenHeight) / 3;

    public GamePanel() {
        this.setSize(maxSize, maxSize);
        this.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        this.setPuzzlePieces(puzzlePieces);
    }

    public static GamePanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new GamePanel();
            return panelInstance;
        }
        return panelInstance;
    }


    // ===============================================================


    boolean gameIsRunning = true;
    @Override
    public void run() {

        while (gameIsRunning) {


            update();


            draw();


            repaint();


            try {
                // TODO: CHANGE THIS FOR IMPROVING YOUR FRAME RATE... (OPTIONAL)
                Thread.sleep(1000 / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    // ==============================================================


    public void update() {
        if (gameFinished) {
            gameIsRunning = false;
        }
        if (gameState.equals("finished")) {
            JOptionPane.showMessageDialog(this, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
            gameFinished = true;
        }

    }


    public void draw() {

    }


    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).img);
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).pieceNumber);
        puzzlePieces.get(j).setImage(copy.img);
        puzzlePieces.get(j).setPieceNumber(copy.pieceNumber);

        if (gameFinished()) {
            gameState = "finished";
        }
    }

    public boolean gameFinished() {
        for (int i = 0; i < 9; i++) {
            int pieceIdentifier = puzzlePieces.get(i).pieceNumber;
            if (pieceIdentifier == 8) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
        System.out.println();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            g.drawImage(piece.img, piece.location.x, piece.location.y, (int) this.getSize().getWidth() / 3, (int) this.getSize().getHeight() / 3, null);
        }
    }



}