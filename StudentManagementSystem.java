import java.util.ArrayList;
import java.util.Scanner;

/**
 * Student Management System
 * Stores student records, displays them, searches by ID, and calculates averages.
 */
public class StudentManagementSystem {

    static class Student {
        private final int id;
        private final String name;
        private final String course;
        private final double mark;

        Student(int id, String name, String course, double mark) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.mark = mark;
        }

        int getId() {
            return id;
        }

        double getMark() {
            return mark;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Name: " + name
                    + " | Course: " + course + " | Mark: " + mark
                    + " | Grade: " + getGrade(mark);
        }
    }

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);
    private static int nextId = 1001;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Register student");
            System.out.println("2. Display all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Show class statistics");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    registerStudent();
                    break;
                case "2":
                    displayStudents();
                    break;
                case "3":
                    searchStudent();
                    break;
                case "4":
                    showStatistics();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1 to 5.");
            }
        }

        System.out.println("Student Management System closed. Goodbye!");
        input.close();
    }

    private static void registerStudent() {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return;
        }

        System.out.print("Enter course or class: ");
        String course = input.nextLine().trim();

        if (course.isEmpty()) {
            System.out.println("Course or class cannot be empty.");
            return;
        }

        double mark;
        try {
            System.out.print("Enter mark (0-100): ");
            mark = Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid mark. Please enter a number.");
            return;
        }

        if (mark < 0 || mark > 100) {
            System.out.println("Mark must be between 0 and 100.");
            return;
        }

        Student student = new Student(nextId++, name, course, mark);
        students.add(student);
        System.out.println("Student registered successfully!");
        System.out.println(student);
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students have been registered yet.");
            return;
        }

        System.out.println("\n--- Registered Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Total students: " + students.size());
    }

    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        int id;

        try {
            id = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a whole number.");
            return;
        }

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Student found:");
                System.out.println(student);
                return;
            }
        }

        System.out.println("No student found with ID " + id + ".");
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No student records available for statistics.");
            return;
        }

        double total = 0;
        double highest = students.get(0).getMark();
        double lowest = students.get(0).getMark();

        for (Student student : students) {
            double mark = student.getMark();
            total += mark;
            if (mark > highest) highest = mark;
            if (mark < lowest) lowest = mark;
        }

        System.out.printf("Number of students : %d%n", students.size());
        System.out.printf("Class average      : %.2f%n", total / students.size());
        System.out.printf("Highest mark       : %.2f%n", highest);
        System.out.printf("Lowest mark        : %.2f%n", lowest);
    }

    private static String getGrade(double mark) {
        if (mark >= 80) return "A";
        if (mark >= 70) return "B";
        if (mark >= 60) return "C";
        if (mark >= 50) return "D";
        return "F";
    }
}
