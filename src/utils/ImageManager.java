package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageManager {
    private static BufferedImage sheet;

    static {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
        } catch (IOException e) {
            System.out.println("No such a file " + e);
        }
    }

    static private final int sheetScale = sheet.getWidth() / 6;

    static public int getSheetScale() {
        return sheetScale;
    }

    static public BufferedImage getSheet() {
        return sheet;
    }
}
