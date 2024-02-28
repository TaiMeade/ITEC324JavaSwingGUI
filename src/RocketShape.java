import java.awt.*;
import java.awt.geom.*;

import static java.lang.Math.round;

/**
 * A house shape.
 */
public class RocketShape implements SceneShape {


    private int x; // Horizontal spawn position
    private int y; // Vertical spawn position
    private int width; // used for some size calculations

    
    // Default Constructor...generates a random position within the screen to spawn the rocket
    public RocketShape() {
        this.x = randomNumberGenerator(10,1800);
        this.y = randomNumberGenerator(100,900);
        width = 75;
    }

    public void draw(Graphics2D g2) {
        Rectangle2D.Double base
                = new Rectangle2D.Double(x, y + width, width * 1.75, width);

        // Path for cone
        Path2D.Double cone = new Path2D.Double();
        cone.moveTo(x + width * 1.75, y + width);
        cone.lineTo(x + width * 2.75, y + width * 1.5);
        cone.lineTo(x + width * 1.75, y + width * 2);
        cone.lineTo(x + width * 1.75, y + width);

        // Path for top fin
        Path2D.Double topFin = new Path2D.Double();
        topFin.moveTo(x + 5, y + width);
        topFin.lineTo(x + width, y + width);
        topFin.lineTo(x + 5, y + width * .5);
        topFin.lineTo(x + 5, y + width);

        // Path for bottom fin
        Path2D.Double bottomFin = new Path2D.Double();
        bottomFin.moveTo(x + 5, y + width * 2);
        bottomFin.lineTo(x + width, y + width * 2);
        bottomFin.lineTo(x + 5, y + width * 2.5);
        bottomFin.lineTo(x + 5, y + width * 2);

        // Rocket window ellipse
        Ellipse2D.Double rocketWindow = new Ellipse2D.Double((int)round(x + width / 1.2), y + width * 1.25, width / 2, width / 2);


        // Drawing and coloring the rocket
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(37,150,190));
        g2.fill(base);
        g2.setColor(new Color(0,0,0));
        g2.draw(base);

        // Red portions (two fins and the cone)
        g2.setColor(Color.red);
        g2.fill(cone);
        g2.fill(bottomFin);
        g2.fill(topFin);
        g2.setColor(Color.black); // For outlines of red portions
        g2.draw(cone);
        g2.draw(topFin);
        g2.draw(bottomFin);

        // Rocket's window drawing and outline
        g2.setStroke(new BasicStroke(3));
        g2.draw(rocketWindow);
        g2.setColor(new Color(224,210,241));
        g2.fillOval((int)round(x + width / 1.2), (int)round(y + width * 1.25), width / 2, width / 2);
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

    // Returns the current x-coordinate of the rocket
    public int getXCoord() {
        return x;
    }

    // Sets the x-coordinate for when the shape goes off the right side of the screen..."wrapping"
    public void setXCoordRight() {
        x = -100;
    }

    // Sets the x-coordinate for when the shape goes off the left side of the screen..."wrapping"
    public void setXCoordLeft() {
        x = 1900;
    }

}
