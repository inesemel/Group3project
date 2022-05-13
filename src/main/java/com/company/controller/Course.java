package com.company.controller;

public enum Course {
    //arts faculty
    HISTORY,
    POETRY,
    PAINTING,

    //sciences faculty
    MATH,
    CHEMISTRY,
    PHYSICS,

    //history faculty
    PHILOSOPHY,
    LATIN,
    PREHISTORY;

    String column() {
        return name().toLowerCase();
    }
}
