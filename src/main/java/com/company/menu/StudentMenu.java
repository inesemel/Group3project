package com.company.menu;

import com.company.controller.StudentController;

import java.util.Scanner;

public class StudentMenu {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1. Add a new student");
        System.out.println("2. Add student's scores");
        System.out.println("3. Edit student's scores");
        System.out.println("4. Print a report card");
        System.out.println("5. Find faculty");
        System.out.println("6. Delete a student");

        System.out.println("Select an option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println(StudentController.addStudent() ? "Successfully added a new student" : "Student not added ");
                break;
            case 2:
                System.out.println(StudentController.addStudentScores() ? "Successfully added scores" : "Scores not added");
                break;
            case 3:
                StudentController.editScore();
                break;
            case 4:
                System.out.println(StudentController.getReportById());
                break;
            case 5:
                System.out.println(StudentController.getFaculty());
                break;
            case 6:
                System.out.println(StudentController.deleteStudent() ? "Student successfully deleted" : "Student not deleted");
            case 7:
                StudentController.getScores();
            case 8:
                StudentController.printList();
            default:
                System.out.println("Invalid option, try again");
                menu();
        }
    }
}
