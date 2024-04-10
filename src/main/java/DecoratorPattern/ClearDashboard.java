package DecoratorPattern;

import Model.Course;
import View.DashboardView;


public class ClearDashboard extends DashboardDecorator {

    //clears all panels off dashboard
    public void clearDashboard()
    {
        MessageDecorator.removeMessagePanel();
        CourseDecorator.removeCoursePanel();
        System.out.println("dashboard cleared");
    }
}