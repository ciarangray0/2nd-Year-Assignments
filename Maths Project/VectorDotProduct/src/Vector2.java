import java.awt.geom.*;
//this class's objects represent the coordinates vector
class Vector2D {
    //x and y coordinates for the vectors
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //getters and setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    //method to increase the values within the vector
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.getX(), this.y + other.getY());
    }
    //method to decrease the values within the vector
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.getX(), this.y - other.getY());
    }
    //method to scale the values within the vector by a scalar
    public Vector2D scale(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }
    //returns the magnitude(length) of the vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    //normalizes the vector (returns vector with size of 1)
    public Vector2D normalize() {
        double magnitude = magnitude();
        if (magnitude == 0)
            return new Vector2D(0, 0);
        return new Vector2D(x / magnitude, y / magnitude);
    }
    //returns the dot product of two vectors

    public double dotProduct(Vector2D other) {
        return this.x * other.getX() + this.y * other.getY();
    }
}

