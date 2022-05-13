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
        System.out.println("4. Print report card");

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
            default:
                System.out.println("Invalid option, try again");
                menu();
        }
    }
}
