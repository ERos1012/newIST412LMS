# IST412 - Group 4: Learning Management System

# M03-A04 Implemented Design Patterns 

| Team-Member ID | Team-Member Name | Design Pattern Implemented    | Classes / Interfaces implementing the Design Pattern                                                                                                                                               |
| -------------- | ---------------- | ----------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| evr5419        | Eric Rosario     | Factory Method and Navigation | Model: Question, MultipleChoiceQuestion, TrueOrFalseQuestion<br>Controller: QuizController<br>Factory: QuestionCreator, MultipleChoiceQuestionCreator, TrueOrFalseQuestionCreator<br>View: QuizApp, MainView |
| klh6157        | Kai Huang        | Facade and Event Calender                           | Controller: EventController, EventCalendarController, FacadeController<br>Model: Event, EventCalendar                                                                                                                                                                                                   |
| bqz5148        | Bryan Zhou       | Decorator and Morphing Control                              | View: CourseView / DecoratorPattern, ClearDashboard, CourseDecorator, DashboardDecorator, MessageDecorator                                                                                                                                                                                                   |
| agn5089        | Anish Nangare    |                               |  


# Test Harness for AssignmentController

This test harness is designed to test the functionalities of the AssignmentController.

## Usage

1. Clone the repository to your local machine.
2. Compile the Java files using a Java compiler.
3. Run the `Main` class to execute the test cases.

## Test Cases

### 1. Test Adding an Assignment

- Creates a new assignment.
- Adds the assignment to the list of assignments.
- Checks if the assignment was successfully added.

### 2. Test Updating an Assignment

- Updates an existing assignment with new details.
- Checks if the assignment was successfully updated.

### 3. Test Removing an Assignment

- Removes an assignment from the list of assignments.
- Checks if the assignment was successfully removed.

### Messaging
1. To send a message from a student to a teacher:
    - Run the `Main` class.
    - Follow the prompts to send a message.
    - Input values: Sender student ID, recipient teacher ID, message content, and timestamp.

### Course Management
1. To add a new course:
    - Run the `Main` class.
    - Follow the prompts to add a new course.
    - Input values: Course name, course code, department, and instructor.

2. To remove a course:
    - Run the `Main` class.
    - Follow the prompts to remove a course.
    - Input values: Course code of the course to remove.

### Quiz Management
1. To add a quiz:
    - Run the `Main` class.
    - Follow the prompts to add a new quiz.
    - Input values: Quiz ID, course ID, max score, quiz name, due date, and list of questions.

### Student Management
1. To add a student:
    - Run the `Main` class.
    - Follow the prompts to add a new student.
    - Input values: Student name, student ID, major, and email.

2. To remove a student:
    - Run the `Main` class.
    - Follow the prompts to remove a student.
    - Input values: Student to remove.

3. To update a student:
    - Run the `Main` class.
    - Follow the prompts to update a student.
    - Input values: Updated student details.

4. To view a student:
    - Run the `Main` class.
    - Follow the prompts to view a student.
    - Input values: Student to view.

5. To get all students in a course:
    - Run the `Main` class.
    - Follow the prompts to get all students in a course.
    - Input values: Course code.

### Teacher Management
1. To add a teacher:
    - Run the `Main` class.
    - Follow the prompts to add a new teacher.
    - Input values: Teacher name, teacher ID, and email.

2. To remove a teacher:
    - Run the `Main` class.
    - Follow the prompts to remove a teacher.
    - Input values: Teacher to remove.

3. To update a teacher:
    - Run the `Main` class.
    - Follow the prompts to update a teacher.
    - Input values: Updated teacher details.

4. To view a teacher:
    - Run the `Main` class.
    - Follow the prompts to view a teacher.
    - Input values: Teacher to view.

5. To get all teachers:
    - Run the `Main` class.
    - Follow the prompts to get all teachers.
    - No input values required.

## Expected Output

- For each test case, the console output will indicate whether the test passed or failed.

## Authentication

- authentication methods are implemented in LoginController. Utilizes usernames and passwords stored in the database.

## Members

1. Eric Rosario
2. Kai Huang
3. Anish Nangare
4. Bryan Zhou

| Team Member ID + Name   |  Contribution in the CM02-A03 System Component API Stubs and Automated System Tests       |
| ----------------------- |  -----------------------------------------------------------------------------------------|
| evr5419 + Eric Rosario  | - Coded the tests for the Course, Grade, Message, and Quiz Controller (part 2) |
| klh6157 + Kai Huang     | - Coded the tests for the Assignment Controller (part 2)                    |
| agn5089 + Anish Nangare | - Coded the tests for the Student, and Teacher Controller (part 2)                     |
| bqz5148 + Bryan Zhou    | - Completed part 1 of the assignment  |

| Team Member ID + Name   |  Contribution in The M02-A02 Project Function Point Analysis and Estimate                 |
| ----------------------- |  -----------------------------------------------------------------------------------------|
| evr5419 + Eric Rosario  | - Programmed the class structure and methods<br>- Exported the Javadoc html documentation |
| klh6157 + Kai Huang     | - Wrote the Word Document detailing class structure and functionality                     |
| agn5089 + Anish Nangare | - Wrote the Word Document detailing class structure and functionality                     |
| bqz5148 + Bryan Zhou    | - Programmed the class structure and methods                                              |

