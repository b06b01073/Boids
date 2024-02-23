public class Vector2d {
    private double x;
    private double y; 

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d() {
        this.x = 0;
        this.y = 0;
    }

    public static Vector2d add(Vector2d vec1, Vector2d vec2) {
        return new Vector2d(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }


    // return (vec1 - vec2)
    public static Vector2d sub(Vector2d vec1, Vector2d vec2) {
        return new Vector2d(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY());
    }


    public static Vector2d div(Vector2d vec, double scalar) {
        return new Vector2d(vec.getX() / scalar, vec.getY() / scalar);
    }

    public static Vector2d mul(Vector2d vec, double scalar) {
        return new Vector2d(scalar * vec.getX(), scalar * vec.getY());
    }


    // return the length of vector
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
    

    // return the length of vector
    public static double getLength(Vector2d vec1, Vector2d vec2) {
        double dx = vec1.getX() - vec2.getX();
        double dy = vec1.getY() - vec2.getY();
        
        return Math.sqrt(dx * dx + dy * dy);
    }

    
    // if the length of v is greater than limit, limit the length to `limit` 
    public static Vector2d limit(Vector2d v, double limit) {
        double len = v.getLength();
        if(len > limit) {
            v = Vector2d.mul(v, limit / len);
        }

        return v;
    }

    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}