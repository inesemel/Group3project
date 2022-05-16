package com.company.controller;

import java.util.ArrayList;
import java.util.List;
import static com.company.controller.Course.*;
import static java.util.Arrays.asList;

public enum Faculty {
    SCIENCES(MATH, CHEMISTRY, PHYSICS),
    HISTORY(PHILOSOPHY, LATIN, PREHISTORY),
    ARTS(Course.HISTORY, POETRY, PAINTING);

    Faculty(Course... courses) {
        this.courses = asList(courses);
    }

    private List<Course> courses;

    static String facultiesText() {
        String facultiesText = "";
        for (Faculty faculty : values()) {
            facultiesText += (", " + faculty.name().toLowerCase());
        }
        return facultiesText.substring(2);
    }

    String coursesText() {
        String coursesText = "";
        for (Course course : courses) {
            coursesText += (", " + course.name().toLowerCase());
        }
        return coursesText.substring(2);
    }

    List listOfCourse() {
        List<String> listOfString = new ArrayList<>();
        for (Course course : courses) {
            listOfString.add(course.name().toLowerCase());
        }
        return listOfString;
    }

    String table() {
        return name().toLowerCase();
    }
}
