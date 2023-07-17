package pl.javastart.task.models;

public class Group {
    private String code;
    private String name;
    private Lecturer lecturer;
    private Student[] students;

    public Group(String code, String name, Lecturer lecturer, int maxStudentsAmount) {
        this.code = code;
        this.name = name;
        this.lecturer = lecturer;
        this.students = new Student[maxStudentsAmount];
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    public Student findStudent(int index) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                return null;
            }
            if (students[i].getIndex() == index) {
                return students[i];
            }
        }
        return null;
    }

    public String getInfo() {
        String studentsInfo = getStudentsInfo();
        return String.format("Kod: %s%nNazwa: %s%nProwadzący: %s%nUczestincy:%n%s", code, name, lecturer.getInfo(), studentsInfo);
    }

    public String getStudentsInfo() {
        StringBuilder studentsInfo = new StringBuilder();
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                studentsInfo.append(students[i].getInfo() + "\n");
            }
        }
        return studentsInfo.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Student[] getStudents() {
        return students;
    }
}
