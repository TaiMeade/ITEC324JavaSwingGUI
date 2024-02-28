import java.awt.*;
import java.awt.geom.*;

/**
 * A shape that is a part of a scene.
 */
public interface SceneShape {
    /**
     * Draws this item.
     *
     * @param g2 the graphics context
     */
    void draw(Graphics2D g2);

    /**
     * Translates this item by a given amount.
     *
     * @param dx the amount to translate in x-direction
     * @param dy the amount to translate in y-direction
     */
    void translate(double dx, double dy);

    // Returns the X coordinate...this is used to help with wrapping
    int getXCoord();

    // Sets the coordinate for wrapping when exiting the right side of the screen
    void setXCoordRight();

    // Sets the coordinate for wrapping when exiting the left side of the screen
    void setXCoordLeft();
}


