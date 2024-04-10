package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FavoritedCoursesDecorator extends JPanel {
    private JPanel originalPanel;
    private List<String> favoritedCourses;

    /**
     * Constructs a new FavoritedCoursesDecorator object.
     * @param originalPanel The original panel to decorate.
     */
    public FavoritedCoursesDecorator(JPanel originalPanel) {
        super(new BorderLayout());
        this.originalPanel = originalPanel;
        this.favoritedCourses = new ArrayList<>();

        add(originalPanel, BorderLayout.CENTER);

        JPanel favoritedCoursesPanel = new JPanel();
        favoritedCoursesPanel.setLayout(new BoxLayout(favoritedCoursesPanel, BoxLayout.Y_AXIS));
        JLabel favoritedLabel = new JLabel("Favorited Courses:");
        favoritedCoursesPanel.add(favoritedLabel);
        for (String course : favoritedCourses) {
            JLabel courseLabel = new JLabel(course);
            favoritedCoursesPanel.add(courseLabel);
        }
        add(favoritedCoursesPanel, BorderLayout.EAST);
    }

    /**
     * Adds a course to the list of favorited courses.
     * @param course The course to add.
     */
    public void addFavoritedCourse(String course) {
        favoritedCourses.add(course);
        updateFavoritedCoursesPanel();
    }

    /**
     * Removes a course from the list of favorited courses.
     * @param course The course to remove.
     */
    public void removeFavoritedCourse(String course) {
        favoritedCourses.remove(course);
        updateFavoritedCoursesPanel();
    }

    /**
     * Updates the panel displaying favorited courses.
     */
    private void updateFavoritedCoursesPanel() {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JPanel && ((JPanel) component).getComponentCount() > 0) {
                Component subComponent = ((JPanel) component).getComponent(0);
                if (subComponent instanceof JLabel && ((JLabel) subComponent).getText().equals("Favorited Courses:")) {
                    JPanel favoritedCoursesPanel = (JPanel) component;
                    favoritedCoursesPanel.removeAll();
                    favoritedCoursesPanel.setLayout(new BoxLayout(favoritedCoursesPanel, BoxLayout.Y_AXIS));
                    favoritedCoursesPanel.add(new JLabel("Favorited Courses:"));
                    for (String course : favoritedCourses) {
                        favoritedCoursesPanel.add(new JLabel(course));
                    }
                    favoritedCoursesPanel.revalidate();
                    favoritedCoursesPanel.repaint();
                }
            }
        }
    }

}