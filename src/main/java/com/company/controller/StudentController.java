package com.company.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.company.dbhelper.DbConnection.getConnection;
import static java.lang.String.format;

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
        String faculty = scanner.next().toLowerCase();

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

    public static boolean addStudentScores() {

        System.out.println("Enter the id of the student: ");
        int studentId = scanner.nextInt();

        System.out.println("Which faculty student is assigned? ");
        System.out.println("1. Arts ");
        System.out.println("2. History ");
        System.out.println("3. Sciences ");

        System.out.println("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:

                try {
                    System.out.println("Enter score of history: ");
                    int historyScore = scanner.nextInt();

                    System.out.println("Enter score of poetry: ");
                    int poetryScore = scanner.nextInt();

                    System.out.println("Enter score of painting: ");
                    int paintingScore = scanner.nextInt();

                    ps = getConnection().prepareStatement("INSERT INTO arts(student_id ,history , poetry, painting) VALUES(" + studentId + ", " + historyScore + ", " + poetryScore + ", " + paintingScore + ") ");
                    ps.execute();
                    return true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            case 2:
                try {
                    System.out.println("Enter score of philosophy: ");
                    int philosophyScore = scanner.nextInt();

                    System.out.println("Enter score of social latin: ");
                    int latinScore = scanner.nextInt();

                    System.out.println("Enter score of economic prehistory: ");
                    int prehistoryScore = scanner.nextInt();

                    ps = getConnection().prepareStatement("INSERT INTO history(student_id ,philosophy , latin, prehistory) VALUES(" + studentId + ", " + philosophyScore + ", " + latinScore + ", " + prehistoryScore + ") ");
                    ps.execute();
                    return true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            case 3:
                try {
                    System.out.println("Enter score of physics: ");
                    int physicsScore = scanner.nextInt();

                    System.out.println("Enter score of math: ");
                    int mathScore = scanner.nextInt();

                    System.out.println("Enter score of chemistry: ");
                    int chemistryScore = scanner.nextInt();

                    ps = getConnection().prepareStatement("INSERT INTO sciences(student_id ,physics , math, chemistry) VALUES(" + studentId + ", " + physicsScore + ", " + mathScore + ", " + chemistryScore + ") ");
                    ps.execute();
                    return true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            default:
                System.out.println("Invalid option. Try again");
                addStudentScores();
        }
        return false;
    }

    public static void editScore() {
        System.out.println("Enter the student id");
        int studentId = scanner.nextInt();

        System.out.println("Please enter faculty: " + Faculty.facultiesText());
        Faculty faculty = Faculty.valueOf(scanner.next().toUpperCase());

        System.out.println("Please enter course: " + faculty.coursesText());
        Course course = Course.valueOf(scanner.next().toUpperCase());

        System.out.println("Please enter score");
        int update = scanner.nextInt();

        try {
            String query = format("UPDATE %s SET %s = ? WHERE student_id = ?", faculty.table(), course.column());
            PreparedStatement statement = getConnection().prepareStatement(query);

            statement.setInt(1, update);
            statement.setInt(2, studentId);

            statement.execute();
            System.out.println("Successfully updated student data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStudentInfo(int id) {

        try{
            ps = getConnection().prepareStatement("SELECT * FROM student_info WHERE id=" + id);
            rs = ps.executeQuery();

//            int studentId;
//            String name, surname, faculty;

            String name = null;
            String surname = null;
            String faculty = null;

            String report = "";
            //loop through the result set and add the necessary values to the student object

            while(rs.next()){
//                studentId = rs.getInt("id");
                name = rs.getString("name");
                surname = rs.getString("surname");
                faculty = rs.getString("faculty");
            }

            report = name + " " + surname + ", Faculty of " + faculty;
            return report;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void getScores() {
        System.out.println("Enter id ");
        int id = scanner.nextInt();
        System.out.println(getStudentScores(id));
    }
    public static String getStudentScores(int id) {
        String faculty = getFaculty(id);
        Faculty faculty1 = Faculty.valueOf(faculty.toUpperCase());
        try{
            ps = getConnection().prepareStatement("SELECT * FROM " + faculty + " WHERE student_id=" + id);
            rs = ps.executeQuery();
            String report = "";

            int subject;

            int i =0;
//            courses = faculty1.values();
            while(rs.next()) {
                while(i < faculty1.listOfCourse().size()) {
                    System.out.println(faculty1.listOfCourse().get(i));
                    subject = rs.getInt(String.valueOf(faculty1.listOfCourse().get(i)).toLowerCase());
                    report += "\r\n" + String.valueOf(faculty1.listOfCourse().get(i)).toUpperCase() + ": " + subject;
                    i++;

                }
            }

            return report;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void printList() {
        System.out.println("Enter id ");
        int id = scanner.nextInt();
        String faculty = getFaculty(id);
        Faculty faculty1 = Faculty.valueOf(faculty.toUpperCase());
        try{
            ps = getConnection().prepareStatement("SELECT * FROM " + faculty + " WHERE student_id=" + id);
            rs = ps.executeQuery();
            String report = null;

//            courses = faculty1.values();
           int i =0;
           while(i < faculty1.listOfCourse().size()) {
                System.out.println(faculty1.listOfCourse().get(i));
                i++;
            }
            System.out.println(faculty1.listOfCourse().size());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String getReportById() {
        System.out.println("Enter the id of the student whose report you want to make: ");
        int id = scanner.nextInt();
        String report = "";
        report = getStudentInfo(id) + "\r\n" + getStudentScores(id);
        return report;

    }

    public static String getFaculty(int id) {
        try{
            ps = getConnection().prepareStatement("SELECT * FROM student_info WHERE id=" + id);
            rs = ps.executeQuery();

            String faculty = null;

            //loop through the result set and add the necessary values to the student object

            while(rs.next()){
                faculty = rs.getString("faculty");
            }
            return faculty;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
    public static String getFaculty() {
        System.out.println("Enter id ");
        int id = scanner.nextInt();
        return getFaculty(id);
    }
    public static boolean deleteStudent(){

        System.out.println("Enter the faculty Sciences, History, Arts");
        System.out.println("Which faculty student is assigned? ");
        System.out.println("1. Arts ");
        System.out.println("2. History ");
        System.out.println("3. Sciences ");
        System.out.println("Select 1, 2 or 3: ");
        int facultyNum = scanner.nextInt();



        switch (facultyNum) {
            case 1:
                try {
                    System.out.println("Enter the id of the student: ");
                    int id = scanner.nextInt();
                    ps = getConnection().prepareStatement("DELETE FROM arts WHERE id=" + id);
                    ps.execute();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

            case 2:
                try {
                    System.out.println("Enter the id of the student: ");
                    int id = scanner.nextInt();
                    ps = getConnection().prepareStatement("DELETE FROM history WHERE id=" + id);
                    ps.execute();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

            case 3:
                try {
                    System.out.println("Enter the id of the student: ");
                    int id = scanner.nextInt();
                    ps = getConnection().prepareStatement("DELETE FROM sciences WHERE id=" + id);
                    ps.execute();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            default:
                System.out.println("Invalid option. Try again");
                deleteStudent();
        }
        return false;
    }

}
