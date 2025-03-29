import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    public void displayCourse() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + getAvailableSlots());
        System.out.println("---------------------------------");
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println(" Successfully registered for " + course.getTitle());
        } else {
            System.out.println(" Course is full. Cannot register.");
        }
    }

    public void dropCourse(String courseCode) {
        for (Course course : registeredCourses) {
            if (course.getCode().equals(courseCode)) {
                course.dropStudent();
                registeredCourses.remove(course);
                System.out.println(" Successfully dropped " + course.getTitle());
                return;
            }
        }
        System.out.println(" You are not registered in this course.");
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println(" No courses registered.");
            return;
        }
        System.out.println("Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println("- " + course.getTitle());
        }
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        mainMenu();
    }

    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Programming", "Learn the basics of Java", 3, "MWF 10:00 AM"));
        courses.add(new Course("CS102", "Data Structures", "Learn about linked lists, trees, graphs, etc.", 2, "TTH 1:00 PM"));
        courses.add(new Course("CS103", "Database Systems", "Introduction to SQL and NoSQL databases", 2, "MWF 3:00 PM"));
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== Student Course Registration System =====");
            System.out.println("1. Register a Student");
            System.out.println("2. Display Available Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    displayCourses();
                    break;
                case 3:
                    registerForCourse();
                    break;
                case 4:
                    dropCourse();
                    break;
                case 5:
                    viewRegisteredCourses();
                    break;
                case 6:
                    System.out.println(" Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println(" Invalid choice. Please try again.");
            }
        }
    }

    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.add(new Student(studentId, name));
        System.out.println(" Student registered successfully.");
    }

    private static void displayCourses() {
        System.out.println("\n===== Available Courses =====");
        for (Course course : courses) {
            course.displayCourse();
        }
    }

    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static void registerForCourse() {
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        
        if (student == null) {
            System.out.println(" Student not found. Please register first.");
            return;
        }

        displayCourses();
        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();

        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                student.registerCourse(course);
                return;
            }
        }
        System.out.println(" Course not found.");
    }

    private static void dropCourse() {
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayRegisteredCourses();
        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();
        student.dropCourse(courseCode);
    }

    private static void viewRegisteredCourses() {
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student == null) {
            System.out.println(" Student not found.");
            return;
        }

        student.displayRegisteredCourses();
    }
}
