package pl.javastart.task.models;

public class Lecturer extends Person {
    private int id;
    private String degree;

    public Lecturer(String firstName, String lastName, int id, String degree) {
        super(firstName, lastName);
        this.id = id;
        this.degree = degree;
    }

    public String getInfo() {
        return String.format("%s %s", degree, super.getInfo());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
