package org.example;

import java.util.ArrayList;
import java.util.Scanner;

class Assignment1PROG6112 {
    private static final ArrayList<StudentRecord> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    saveStudent();
                    break;
                case "2":
                    searchStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    studentReport();
                    break;
                case "5":
                    exitStudentApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nSTUDENT MANAGEMENT APPLICATION");
        System.out.println("1. Capture a new student");
        System.out.println("2. Search for a student");
        System.out.println("3. Delete a student");
        System.out.println("4. Print student report");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void saveStudent() {
        String id = getInput("Enter student ID: ");
        String name = getInput("Enter student name: ");
        int age = getValidAge();
        String email = getInput("Enter student email: ");
        String course = getInput("Enter course: ");

        StudentRecord newStudent = new StudentRecord(id, name, age, email, course);
        students.add(newStudent);
        System.out.println("Student details successfully saved: " + newStudent);
    }

    private static void searchStudent() {
        String id = getInput("Enter student ID to search: ");
        StudentRecord student = findStudentByID(id);

        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void deleteStudent() {
        String id = getInput("Enter student ID to delete: ");
        StudentRecord student = findStudentByID(id);

        if (student != null) {
            System.out.println("Student found: " + student);
            String confirmation = getInput("Do you really want to delete this student? (yes/no): ");
            if (confirmation.equalsIgnoreCase("yes")) {
                students.remove(student);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student deletion cancelled.");
            }
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void studentReport() {
        if (students.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }

        System.out.println("Student Report:");
        for (StudentRecord student : students) {
            System.out.println(student);
        }
    }

    private static void exitStudentApplication() {
        System.out.println("Exiting the application. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getValidAge() {
        while (true) {
            String input = getInput("Enter student age (16 or older): ");
            try {
                int age = Integer.parseInt(input);
                if (age >= 16) return age;
                System.out.println("Age must be 16 or older. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid age.");
            }
        }
    }

    private static StudentRecord findStudentByID(String id) {
        for (StudentRecord student : students) {
            if (student.studentID.equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Inner class to represent a student record
    private static class StudentRecord {
        String studentID, name, email, course;
        int age;

        StudentRecord(String studentID, String name, int age, String email, String course) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.email = email;
            this.course = course;
        }

        @Override
        public String toString() {
            return String.format("ID: %s, Name: %s, Age: %d, Email: %s, Course: %s",
                    studentID, name, age, email, course);
        }
    }
}