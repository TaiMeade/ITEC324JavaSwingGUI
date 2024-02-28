import java.awt.*;
import java.awt.geom.*;

import static java.lang.Math.round;

public class BirdShape implements SceneShape  {

    private int x; // Horizontal spawn position
    private int y; // Vertical spawn position
    private int width; // used for some size calculations

    // Default Constructor...generates a random position within the screen to spawn the bird
    public BirdShape() {
        this.x = randomNumberGenerator(10,1800);
        this.y = randomNumberGenerator(100,900);
        this.width = 75;
    }

    public void draw(Graphics2D g2) {

        g2.setStroke(new BasicStroke(1.25F));

        // Path for the bird's beak
        Path2D.Double birdBeak = new Path2D.Double();
        birdBeak.moveTo(x, y + 5);
        birdBeak.lineTo(x + 17, y + 15);
        birdBeak.lineTo(x, y + 25);
        birdBeak.lineTo(x, y + 5);
        g2.setColor(new Color(150,75,0)); // sets color to brown
        g2.fill(birdBeak);
        g2.setColor(Color.BLACK);
        g2.draw(birdBeak);

        // Path for the bird's body
        Path2D.Double birdBody = new Path2D.Double();
        birdBody.moveTo(x, y);
        birdBody.lineTo(x - width,y + .75 * width);
        birdBody.curveTo(x - width, y + width, x + .5 * width, y + width, x, y);
        g2.setColor(Color.lightGray);
        g2.fill(birdBody);
        g2.setColor(Color.BLACK);
        g2.draw(birdBody);

        // Path for the bird's wing
        Path2D.Double birdWing = new Path2D.Double();
        birdWing.moveTo(x - 15, y + 15);
        birdWing.lineTo(x - 80, y + 40);
        birdWing.curveTo(x - 80, y + 40, x - 28, y + 87, x - 15, y + 15);
        g2.setColor(new Color(128,128,128));
        g2.fill(birdWing);
        g2.setColor(Color.black);
        g2.draw(birdWing);

        // Ellipse for the bird's eye
        Ellipse2D.Double birdEye = new Ellipse2D.Double(x - 5,y + 5,5,5);
        g2.setColor(new Color(150,75,0)); // sets color to brown
        g2.fill(birdEye);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        g2.draw(birdEye);

    }

    // Moves the shape a certain number of pixels horizontally (dx) and vertically (dy)
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // Generates a random integer from the min to the max...used for spawning the item in a random place
    public int randomNumberGenerator(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }

    // Returns the current x-coordinate of the bird (-75 to adjust for size/location of the x-coordinate
    public int getXCoord() {
        return x - 75;
    }

    // Sets the x-coordinate for when the shape goes off the right side of the screen..."wrapping"
    public void setXCoordRight() {
        x = 0;
    }

    // Sets the x-coordinate for when the shape goes off the left side of the screen..."wrapping"
    public void setXCoordLeft() {
        x = 1960;
    }

}