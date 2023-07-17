package pl.javastart.task.logic;

import pl.javastart.task.models.Grade;
import pl.javastart.task.models.Group;
import pl.javastart.task.models.Lecturer;
import pl.javastart.task.models.Student;

public class University {
    private static final int MAX_STUDENTS_AMOUNT_IN_GROUP = 15;
    private static final int MAX_CLASSES_AMOUNT = 15;
    private Student[] students;
    private Lecturer[] lecturers;
    private Group[] groups;

    public University(int maxStudentsAmount, int maxLecturersAmount, int maxGroupsAmount) {
        this.students = new Student[maxStudentsAmount];
        this.lecturers = new Lecturer[maxLecturersAmount];
        this.groups = new Group[maxGroupsAmount];
    }

    public Student addStudent(String firstName, String lastName, int index) {
        Student student = new Student(firstName, lastName, index, MAX_CLASSES_AMOUNT);
        for(int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                return student;
            }
        }
        return null;
    }

    public Lecturer addLecturer(String firstName, String lastName, int id, String degree) {
        Lecturer lecturer = new Lecturer(firstName, lastName, id, degree);
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] == null) {
                lecturers[i] = lecturer;
                return lecturer;
            }
        }
        return null;
    }

    public Group addGroup(String code, String name, Lecturer lecturer) {
        Group group = new Group(code, name, lecturer, MAX_STUDENTS_AMOUNT_IN_GROUP);
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                groups[i] = group;
                return group;
            }
        }
        return null;
    }

    public void addStudentToGroup(String firstName, String lastName, int index, Group group) {
        Student student = findStudent(index);
        if (student == null) {
            student = addStudent(firstName, lastName, index);
            group.addStudent(student);
        } else if (isStudentInGroup(group, index)) {
            System.out.printf("Student o indeksie %d jest już w grupie %s%n", index, group.getCode());
        } else {
            group.addStudent(student);
        }
    }

    private boolean isStudentInGroup(Group group, int index) {
        Student[] students = group.getStudents();
        for (int i = 0; i < students.length; i++) {
            if(students[i] != null && students[i].getIndex() == index) {
                return true;
            }
        }
        return false;
    }

    public void addGrade(Student student, Group group, double value) {
        Grade[] grades = student.getGrades();
        if (!doesGradeExist(grades, group.getCode())) {
            Grade grade = new Grade(student, group, value);
            insertGrade(grades, grade);
        }
    }

    private void insertGrade(Grade[] grades, Grade grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = grade;
                break;
            }
        }
    }

    private boolean doesGradeExist(Grade[] grades, String groupCode) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] != null && grades[i].getGroup().equals(groupCode)) {
                return true;
            }
        }
        return false;
    }

    public Student findStudent(int index) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getIndex() == index) {
                return students[i];
            }
        }
        return null;
    }

    public Lecturer findLecturer(int id) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null && lecturers[i].getId() == id) {
                return lecturers[i];
            }
        }
        return null;
    }

    public Group findGroup(String code) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && groups[i].getCode().equals(code)) {
                return groups[i];
            }
        }
        return null;
    }

    public Grade findGradeForStudent(Student student, String groupCode) {
        Grade[] grades = student.getGrades();
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] != null && grades[i].getGroup().getCode().equals(groupCode)) {
                return grades[i];
            }
        }
        return null;
    }

    public Student[] getStudents() {
        return students;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public Group[] getGroups() {
        return groups;
    }
}
