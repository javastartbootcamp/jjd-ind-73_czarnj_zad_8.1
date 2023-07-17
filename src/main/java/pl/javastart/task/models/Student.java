package pl.javastart.task.models;

public class Student extends Person {
    private int index;

    public Student(String firstName, String lastName, int index) {
        super(firstName, lastName);
        this.index = index;
    }

    public String getInfo() {
        return String.format("%d %s", index, super.getInfo());
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
