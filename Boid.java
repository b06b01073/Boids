import java.util.ArrayList;
import java.util.Random;

public class Boid {
    private Vector2d position;
    private Vector2d velocity; // the boid is moving along the `direction` direction
    private Vector2d acceleration;

    private double cohereRange = 100;
    private double alignRange = 60;
    private double separateRange = 80;

    private double cohereLimit = 2; 
    private double velocityLimit = 10;
    private double alignLimit =  4;
    private double separateLimit = 2;

    
    // init the position and gives the boid a random direction
    public Boid(double x, double y) {
        this.position = new Vector2d(x, y);
        
        Random rand = new Random();
        this.velocity = new Vector2d(rand.nextDouble() - 0.5, rand.nextDouble() - 0.5);
    }

    
    public Vector2d cohere(ArrayList<Boid> flock) {
        Vector2d steering = new Vector2d();
        int count = 0;
        for(Boid boid: flock) {
            if(canDetect(boid, this.cohereRange)) {
                steering = Vector2d.add(steering, boid.position);
                count++;
            } 
        }


        if(count == 0) {
            return steering;
        }

        steering = Vector2d.div(steering, count);
        steering = Vector2d.sub(steering, this.position);
        return Vector2d.limit(steering, this.cohereLimit);
    }


    public Vector2d align(ArrayList<Boid> flock) {
        Vector2d steering = new Vector2d();
        int count = 0;
        for(Boid boid: flock) {
            if(canDetect(boid, this.alignRange)) {
                steering = Vector2d.add(steering, boid.velocity);
                count++;
            }
        }

        if(count == 0)
            return steering;

        steering = Vector2d.div(steering, count);
        steering = Vector2d.limit(steering, alignLimit);
        return steering;
    }


    public Vector2d separate(ArrayList<Boid> flock) {
        Vector2d steering = new Vector2d();
        for(Boid boid: flock) {
            if(canDetect(boid, this.separateRange)) {
                Vector2d diff = Vector2d.sub(this.position, boid.position);
                diff = Vector2d.div(diff, Vector2d.getLength(diff));
                steering = Vector2d.add(steering, diff);
            }
        }

        steering = Vector2d.limit(steering, separateLimit);
        return steering;
    }


    private boolean canDetect(Boid other, double range) {
        if(other != this && Vector2d.getLength(this.position, other.position) <= range) {
            return true;
        }
        return false;
    }
    


    // update the pos of boid
    public void update(ArrayList<Boid> flock) {
        // update the direction
        this.acceleration = new Vector2d();
        this.acceleration = Vector2d.add(this.acceleration, this.cohere(flock)); // moves toward the center of mass
        this.acceleration = Vector2d.add(this.acceleration, this.align(flock));
        this.acceleration = Vector2d.add(this.acceleration, this.separate(flock));

        this.velocity = Vector2d.add(this.velocity, this.acceleration);
        this.velocity = Vector2d.limit(this.velocity, this.velocityLimit);
        this.position = Vector2d.add(this.position, this.velocity);
    }


    public Vector2d getPosition() {
        return this.position;
    }



    public void setX(double x) {
        this.position.setX(x);
    }


    public void setY(double y) {
        this.position.setY(y);
    }
}

