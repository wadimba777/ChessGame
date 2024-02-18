package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageManager {

    private BufferedImage sheet; {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
        } catch (IOException e) {
            System.out.println("No such a file " + e);
        }
    }
    protected final int sheetScale = sheet.getWidth() / 6;

    public BufferedImage getSheet() {
        return sheet;
    }
}
