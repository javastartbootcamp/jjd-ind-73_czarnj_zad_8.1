package pl.javastart.task.logic;

import pl.javastart.task.models.Grade;
import pl.javastart.task.models.Group;
import pl.javastart.task.models.Lecturer;
import pl.javastart.task.models.Student;

import java.util.Locale;

public class University {
    private Student[] students;
    private Lecturer[] lecturers;
    private Group[] groups;
    private Grade[] grades;
    private int studentCounter;
    private int lecturerCounter;
    private int groupCounter;
    private int gradeCounter;
    private StringBuilder stringBuilder = new StringBuilder();

    public University(int maxStudentsAmount, int maxLecturersAmount, int maxGroupsAmount) {
        this.students = new Student[maxStudentsAmount];
        this.lecturers = new Lecturer[maxLecturersAmount];
        this.groups = new Group[maxGroupsAmount];
        this.grades = new Grade[maxStudentsAmount * maxGroupsAmount];
    }

    public void addStudent(Student student) {
        if (studentCounter < students.length) {
            students[studentCounter++] = student;
        }
    }

    public void addLecturer(Lecturer lecturer) {
        if (lecturerCounter < lecturers.length) {
            lecturers[lecturerCounter++] = lecturer;
        }
    }

    public void addGroup(Group group) {
        if (groupCounter < groups.length) {
            groups[groupCounter++] = group;
        }
    }

    public void addGrade(Grade grade) {
        if (!doesGradeExist(grade) && gradeCounter < grades.length) {
            grades[gradeCounter++] = grade;
        }
    }

    public void addStudentToGroup(Student student, Group group) {
        if (group.containStudent(student)) {
            System.out.printf("Student o indeksie %d jest już w grupie %s%n", student.getIndex(),
                    group.getCode());
        } else {
            group.addStudent(student);
        }
    }

    private boolean doesGradeExist(Grade grade) {
        int studentIndex = grade.getStudent().getIndex();
        String groupCode = grade.getGroup().getCode();
        for (int i = 0; i < gradeCounter; i++) {
            if (grades[i].getGroup().getCode().equals(groupCode) &&
                    grades[i].getStudent().getIndex() == studentIndex) {
                return true;
            }
        }
        return false;
    }

    public Student findStudent(int index) {
        for (int i = 0; i < studentCounter; i++) {
            if (students[i].getIndex() == index) {
                return students[i];
            }
        }
        return null;
    }

    public Lecturer findLecturer(int id) {
        for (int i = 0; i < lecturerCounter; i++) {
            if (lecturers[i].getId() == id) {
                return lecturers[i];
            }
        }
        return null;
    }

    public Group findGroup(String code) {
        for (int i = 0; i < groupCounter; i++) {
            if (groups[i].getCode().equals(code)) {
                return groups[i];
            }
        }
        return null;
    }

    public Grade findGradeForStudent(Student student, Group group) {
        for (int i = 0; i < gradeCounter; i++) {
            if (grades[i].getStudent().getIndex() == student.getIndex() &&
                    grades[i].getGroup().getCode().equals(group.getCode())) {
                return grades[i];
            }
        }
        return null;
    }

    public String getGradesForStudentReport(Student student) {
        cleanStringBuilder();
        for (int i = 0; i < gradeCounter; i++) {
            if (grades[i] == null) {
                break;
            } else if (grades[i].getStudent().getIndex() == student.getIndex()) {
                stringBuilder.append(grades[i].getInfo()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String getGradesForGroupReport(Group group) {
        cleanStringBuilder();
        for (int i = 0; i < gradeCounter; i++) {
            if (grades[i] == null) {
                break;
            } else if (grades[i].getGroup().getCode().equals(group.getCode())) {
                stringBuilder.append(String.format(Locale.ENGLISH, "%s %s: %.1f%n",
                        grades[i].getStudent().getFirstName(), grades[i].getStudent().getLastName(),
                        grades[i].getValue()));
            }
        }
        return stringBuilder.toString();
    }

    private void cleanStringBuilder() {
        stringBuilder.delete(0, stringBuilder.toString().length());
    }

    public String getStudentsReport() {
        cleanStringBuilder();
        for (int i = 0; i < studentCounter; i++) {
            stringBuilder.append(students[i].getInfo()).append("\n");
        }
        return stringBuilder.toString();
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

    public Grade[] getGrades() {
        return grades;
    }
}
