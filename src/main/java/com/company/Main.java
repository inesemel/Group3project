package com.company;

import com.company.controller.StudentController;
import com.company.dbhelper.DbConnection;
import com.company.menu.StudentMenu;
import com.company.students.Student;

public class Main {
    public static void main(String[] args) {
//        DbConnection.getConnection();


        StudentMenu.menu();
    }

}
