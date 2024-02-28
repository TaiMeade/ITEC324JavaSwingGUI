import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * A UFO shape.
 */
public class UFOShape implements SceneShape {

    private int x; // Horizontal spawn position
    private int y; // Vertical spawn position
    private int width; // used for some size calculations


    // Default Constructor...generates a random position within the screen to spawn the rocket
    public UFOShape() {
        this.x = randomNumberGenerator(10,1800);
        this.y = randomNumberGenerator(100,900);
        this.width = 150;
    }

    // Handles drawing the UFO
    public void draw(Graphics2D g2) {
        Ellipse2D.Double body = new Ellipse2D.Double(x + width / 4, y + width / 5, width, width / 5);
        Ellipse2D.Double cab = new Ellipse2D.Double(x + width / 2, y + width / 18, width / 2, width / 3);
        Ellipse2D.Double lowerBody = new Ellipse2D.Double(x + width / 4, y + width / 5, width, width / 4);

        // Drawing and coloring the UFO
        g2.setStroke(new BasicStroke(3.5F));
        g2.draw(cab);
        g2.setColor(new Color(175,223,229));
        g2.fill(cab);
        g2.setColor(new Color(83,83,83));
        g2.fill(lowerBody);
        g2.setColor(new Color(128, 128, 128));
        g2.fill(body);
        g2.setStroke(new BasicStroke(1.85F));
        g2.setColor(Color.black);

        // outlines to add more detail
        g2.draw(lowerBody);
        g2.draw(body);
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

    // Returns the current x-coordinate of the UFO
    public int getXCoord() {
        return x;
    }

    // Sets the x-coordinate for when the shape goes off the right side of the screen..."wrapping"
    public void setXCoordRight() {
        x = -75;
    }

    // Sets the x-coordinate for when the shape goes off the left side of the screen..."wrapping"
    public void setXCoordLeft() {
        x = 1900;
    }

}