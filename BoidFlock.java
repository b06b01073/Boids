import java.util.ArrayList;
import java.util.Random;

public class BoidFlock {
    ArrayList<Boid> flock = new ArrayList<>();


    public BoidFlock(int flockSize, double x, double y, double width, double height) {
        Random rand = new Random();
        for(int i = 0; i < flockSize; i++) {

            // it should be noted that the origin is at the top-left corner (origin or JPanel)
            double boid_x = rand.nextDouble() * width + x; // scale the size to width and move the origin to x
            double boid_y = rand.nextDouble() * height + y; // scale the size to height and move the origin to y

            this.flock.add(new Boid(boid_x, boid_y));
        }
    }

    
    public void update() {
        for(Boid boid: this.flock) {
            boid.update(this.flock);
        }
    }
}
