package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(850, 850));
        frame.setLocationRelativeTo(null);

        Board board = new Board();
        frame.add(board);

        frame.setVisible(true);
    }
}