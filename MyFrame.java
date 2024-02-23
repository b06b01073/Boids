import java.awt.Dimension;

import javax.swing.*;

public class MyFrame extends JFrame{
    private final int WIDTH = 1200;
    private final int HEIGHT = 1000;

    public MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLocationRelativeTo(null);
        

        this.add(new MyPanel());
    }
}
