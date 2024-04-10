package View;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JPanel {
    private FavoritedCoursesDecorator decorator;


    /**
     * Constructs a new AssignmentView object.
     */
    public DashboardView() {
        super();

        // Create a panel to hold the labels, text fields, and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        // Add the panel to the MainView
        add(panel);
    }

    /**
     * The main method to launch the DashBoardView.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { new DashboardView(); });
    }
}