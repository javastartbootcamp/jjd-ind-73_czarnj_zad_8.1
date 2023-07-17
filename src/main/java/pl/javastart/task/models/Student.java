package pl.javastart.task.models;

public class Student extends Person {
    private int index;
    private Grade[] grades;

    public Student(String firstName, String lastName, int index, int maxClassesAmount) {
        super(firstName, lastName);
        this.index = index;
        this.grades = new Grade[maxClassesAmount];
    }

    public String getInfo() {
        return String.format("%d %s", index, super.getInfo());
    }

    public String getGradesList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] != null) {
                stringBuilder.append(grades[i].getInfo()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Grade[] getGrades() {
        return grades;
    }
}
