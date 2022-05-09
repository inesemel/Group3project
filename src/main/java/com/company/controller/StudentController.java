package com.company.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.company.dbhelper.DbConnection.getConnection;

public class StudentController {

    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addStudent() {

        System.out.println("Enter the name of the student: ");
        String name = scanner.next();

        System.out.println("Enter the surname of the student: ");
        String surname = scanner.next();

        System.out.println("Enter the faculty (Sciences, History, Arts): ");
        String faculty = scanner.next();

        try {
            // INSERT INTO student_info () VALUES('name', 'surname', 'faculty');
            ps = getConnection().prepareStatement("INSERT INTO student_info(name, surname, faculty) " +
                    "VALUES('" + name + "', '" + surname + "', '" + faculty + "')");
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Database error");
            return false;
        }


    }


}
