import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MyPanel extends JPanel implements ActionListener{
    private final int UPDATE_INTERVAL = 20;
    private final int flockSize = 200;

    private BoidFlock boidFlock = new BoidFlock(flockSize, 0, 0, 800,  600);;
    
    private Color bgColor = Color.darkGray;
    private Color boidColor = Color.CYAN;

    private int boidWidth = 4;
    private int boidHeight = 4;
    

    public MyPanel() {
        // this.setBackground(Color.darkGray);
        
        Timer timer = new Timer(UPDATE_INTERVAL, this);
        timer.start();
    }
    
    
    public void paintComponent(Graphics g) {
        // clear the panel
        g.setColor(this.bgColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());


        // plot the boid
        for(Boid boid: this.boidFlock.flock) {
            g.setColor(boidColor);
            g.fillOval((int)boid.getPosition().getX(), (int)boid.getPosition().getY(), this.boidWidth, this.boidHeight);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.boidFlock.update(); // update the position of flock
        for(Boid boid: this.boidFlock.flock) {
            Vector2d position = boid.getPosition();
            wrap(position);
        }

        repaint();
    }

    
    // wrap around if the boid hit the border of panel
    public void wrap(Vector2d vec) {

        double x = vec.getX();
        if(x < 0) {
            vec.setX(this.getWidth() - 1);
        }
        else if(x >= this.getWidth()) {
            vec.setX(0);
        }

        double y = vec.getY();
        if(y < 0) {
            vec.setY(this.getHeight() - 1);
        }
        else if(y >= this.getHeight()){
            vec.setY(0);
        }
    }
}
