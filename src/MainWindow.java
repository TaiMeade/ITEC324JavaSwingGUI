import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MainWindow {

    // Declare/initialize variables
    protected JFrame frame; // main window
    protected JFrame newFrame; // sub window
    protected ScenePanel subPanel; // panel within the sub window
    protected Dimension BUTTON_SIZE = new Dimension(100,50); // default button size

    // The main window of the program, with the main buttons.  Creates a new window via a function when certain conditions are met (based on criteria given in instructions)
    public MainWindow() {

        frame = new JFrame(); // creating instance of JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ScenePanel panel = new ScenePanel();

        // Create checkbox group
        JCheckBox birdBox = new JCheckBox("Bird");
        JCheckBox rocketBox = new JCheckBox("Rocket");
        JCheckBox ufoBox = new JCheckBox("UFO");

        // Show Button
        JButton showButton = new JButton("Show");
        showButton.setPreferredSize(BUTTON_SIZE);
        showButton.addActionListener(new ActionListener() {
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (subPanel == null) {
                                                                createSubWindow();
                                                            }
                                                            else {
                                                                newFrame.setVisible(true);
                                                            }
                                                        }
                                                    });

        // Add button
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(BUTTON_SIZE);
        addButton.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {

                                                                // If the sub window does not exist, then create it
                                                                if (subPanel == null) {
                                                                    createSubWindow();
                                                                }

                                                                // unpauses translations if they're paused when the add button is pressed
                                                                if (subPanel.getTranslateAmount() == 0) {
                                                                    subPanel.pauseTranslations();
                                                                }

                                                                // If subWindow/other frame is hidden set it to visible
                                                                if (!newFrame.isVisible()) {
                                                                    newFrame.setVisible(true);
                                                                }

                                                                // Add the shapes, but only if their box is checked
                                                                if (birdBox.isSelected()) {
                                                                    subPanel.add(new BirdShape());
                                                                }
                                                                if (rocketBox.isSelected()) {
                                                                    subPanel.add(new RocketShape());
                                                                }
                                                                if (ufoBox.isSelected()) {
                                                                    subPanel.add(new UFOShape());
                                                                }
                                                            }
                                                          });

        // Remove button
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(BUTTON_SIZE);
        removeButton.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent e) {
                                                                    // Removes the most recently added shape, but only if the sub window exists AND is visible
                                                                    if (!(newFrame == null) && newFrame.isVisible()) {
                                                                        subPanel.removeLast();
                                                                    }
                                                                    // else do nothing
                                                                }
                                                            });

        // Pause button
        JButton pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(BUTTON_SIZE);
        pauseButton.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent e) {
                                                                    subPanel.pauseTranslations();
                                                                }
                                                            });

        // Change Direction button
        JButton flipDirection = new JButton("Flip Direction");
        flipDirection.setPreferredSize(BUTTON_SIZE);
        flipDirection.addActionListener(new ActionListener() {
                                                                // Reverse the direction of all shapes (does nothing if they are paused)
                                                                public void actionPerformed(ActionEvent e) {
                                                                    subPanel.invertTranslateAmount();
                                                                }
                                                            });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(BUTTON_SIZE);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });



        // Button/control panel
        JPanel buttons = new JPanel();
        buttons.add(showButton); // first button
        buttons.add(addButton); // second button
        buttons.add(removeButton); // third button
        buttons.add(pauseButton); // fourth button
        buttons.add(flipDirection); // fifth button
        buttons.add(exitButton); // sixth button

        // Checkboxes to add to the button/control panel of the main window
        buttons.add(birdBox); // bird checkbox
        buttons.add(rocketBox); // rocket checkbox
        buttons.add(ufoBox); // UFO checkbox

        // Add the button panel to the main ScenePanel
        panel.add(buttons, BorderLayout.WEST);

        // Add the ScenePanel to the frame/window, set the size to the default size of a 1080p monitor (1920x1080), set the title, and make it visible
        frame.add(panel);
        frame.setSize(new Dimension(1920,1080));
        frame.setTitle("Tai's GUI");
        frame.setVisible(true); // making the frame visible
    }


    // Method for creating the sub window
    public void createSubWindow() {

        // Create
        newFrame = new JFrame("Sub Window");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes only this window, rather than the entire program

        subPanel = new ScenePanel();

        // Hide Button
        JButton hideButton = new JButton("Hide");
        hideButton.setPreferredSize(BUTTON_SIZE);
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFrame.setVisible(false);
                subPanel.pauseTranslations();
            }
        });

        // Exit button
        JButton subExitButton = new JButton("Exit");
        subExitButton.setPreferredSize(BUTTON_SIZE);
        subExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFrame.dispatchEvent(new WindowEvent(newFrame, WindowEvent.WINDOW_CLOSING));
                subPanel = null; // removes all shapes by completely deleting the subPanel which contained all the shapes
                newFrame = null; // requires a new sub window to be created
            }
        });

        // Button panel
        JPanel buttons = new JPanel();
        buttons.add(hideButton);
        buttons.add(subExitButton);

        // Add the button/control panel to the subPanel
        subPanel.add(buttons, BorderLayout.SOUTH);

        // Add the respective button panel to the sub window, make the default size slightly smaller than the main window, set the title, make it visible
        newFrame.add(subPanel);
        newFrame.setSize(new Dimension(1800,900));
        newFrame.setTitle("Tai's GUI");
        newFrame.setVisible(true); // making the frame visible
    }
}