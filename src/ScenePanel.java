import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

/**
 * A panel that shows a scene composed of shapes.
 */
public class ScenePanel extends JPanel implements ActionListener {

    // Declare/initialize variables
    protected Timer timer = new Timer(5, this); // Handles how often to translate the shape a set amount (1, 0, or -1)
    private int translateAmount = 1; // The default amount to translate the shapes
    private ArrayList shapes; // ArrayList of the shapes
    private int lastTranslateAmount; // Used to assist with un-pausing and resuming the previous direction

    // Default Constructor
    public ScenePanel() {
        shapes = new ArrayList();
    }

    /**
     * Adds a shape to the scene.
     *
     * @param s the shape to add
     */
    public void add(SceneShape s) {
        shapes.add(s);
        repaint();
    }

    /**
     * Removes the last added shape from the scene.
     */
    public void removeLast() {

        // the try catch blocks get rid of errors for when the user tries to remove a shape when there are none to be removed...only showed up in terminal and did not cause issues but got rid of it anyways.
        try {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
        catch (IndexOutOfBoundsException e) {
            //do nothing
        }
    }

    // Handles painting the shapes
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < shapes.size(); i++) {
            SceneShape s = (SceneShape) shapes.get(i);
            s.draw(g2);
            timer.start(); // tells the program how often to translate the shapes
        }
    }

    // Handles translating all shapes across the screen.
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < shapes.size(); i++) {
            SceneShape s = (SceneShape) shapes.get(i);
            s.translate(translateAmount, 0);
            if (s.getXCoord() > 1900) {
                s.setXCoordRight();
            }
            if (s.getXCoord() < -101) {
                s.setXCoordLeft();
            }
            repaint();
        }
    }

    // Reverses the translation direction (flip direction button)
    public void invertTranslateAmount() {
        translateAmount *= -1;
    }

    // Pauses the movement of the shapes
    public void pauseTranslations() {
        if (translateAmount == 0) {
            translateAmount = lastTranslateAmount;
        }
        else if (translateAmount != 0) {
            lastTranslateAmount = translateAmount;
            translateAmount = 0;
        }

    }

    // used to help determine if the shapes need to be un-paused when the 'Add' button is pressed
    public int getTranslateAmount() {
        return translateAmount;
    }

}
