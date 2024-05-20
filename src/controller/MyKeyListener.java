package controller;

import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int missingPieceIndex = GamePanel.getInstance().missingPiece;
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (missingPieceIndex % 3 == 2) {
                return;
            }
            GamePanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 1);
            GamePanel.getInstance().setMissingPiece(missingPieceIndex + 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if (missingPieceIndex % 3 == 0) {
                return;
            }
            GamePanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 1);
            GamePanel.getInstance().setMissingPiece(missingPieceIndex - 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if (missingPieceIndex <= 2) {
                return;
            }
            GamePanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 3);
            GamePanel.getInstance().setMissingPiece(missingPieceIndex - 3);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if (missingPieceIndex >= 6) {
                return;
            }
            GamePanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 3);
            GamePanel.getInstance().setMissingPiece(missingPieceIndex + 3);
        }

        if (GamePanel.getInstance().gameState.equals("finished")) {
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}