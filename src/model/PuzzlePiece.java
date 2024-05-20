package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    public Image img;
    public int pieceNumber;
    public Location location;

    public PuzzlePiece(String imageName) {
        try {
            img = ImageIO.read(new File("src/assets/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!imageName.equals("missing.jpg")) {
            pieceNumber = Integer.parseInt(imageName.substring(0, 1)) - 1;
        } else {
            pieceNumber = 8;
        }
    }

    public PuzzlePiece(String ImagePath, Location location) {
        this(ImagePath);
        this.location = location;
    }

    public PuzzlePiece(Image img, Location location, int pieceIdentifier) {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(img, location, pieceNumber);

        return clone;
    }
}