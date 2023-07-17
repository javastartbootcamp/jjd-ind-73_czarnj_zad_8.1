package pl.javastart.task.models;

import java.util.Locale;

public class Grade {
    private Student student;
    private Group group;
    private double value;

    public Grade(Student student, Group group, double value) {
        this.student = student;
        this.group = group;
        this.value = value;
    }

    public String getInfo() {
        return String.format(Locale.ENGLISH, "%s: %.1f", group.getName(), value);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
