package pl.javastart.task;

import pl.javastart.task.logic.University;
import pl.javastart.task.models.Grade;
import pl.javastart.task.models.Group;
import pl.javastart.task.models.Lecturer;
import pl.javastart.task.models.Student;

import java.util.Locale;

public class UniversityApp {
    private static final int MAX_STUDENTS_AMOUNT = 500;
    private static final int MAX_LECTURERS_AMOUNT = 150;
    private static final int MAX_GROUPS_AMOUNT = 30;
    private final University university;

    public UniversityApp() {
        this.university = new University(MAX_STUDENTS_AMOUNT, MAX_LECTURERS_AMOUNT, MAX_GROUPS_AMOUNT);
    }

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */
    public void createLecturer(int id, String degree, String firstName, String lastName) {
        Lecturer lecturer = university.findLecturer(id);
        if (lecturer == null) {
            university.addLecturer(firstName, lastName, id, degree);
        } else {
            System.out.printf("Prowadzący z id %d już istnieje%n", id);
        }
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
    public void createGroup(String code, String name, int lecturerId) {
        Lecturer lecturer = university.findLecturer(lecturerId);
        if (lecturer == null) {
            System.out.printf("Prowadzący o id %d nie istnieje%n", lecturerId);
        } else {
            Group group = university.findGroup(code);
            if (group == null) {
                university.addGroup(code, name, lecturer);
            } else {
                System.out.printf("Grupa %s już istnieje%n", code);
            }
        }
    }


    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = university.findGroup(groupCode);
        if (group == null) {
            System.out.printf("Grupa %s nie istnieje%n", groupCode);
        } else {
            university.addStudentToGroup(firstName, lastName, index, group);
        }
    }


    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */
    public void printGroupInfo(String groupCode) {
        Group group = university.findGroup(groupCode);
        if (group == null) {
            System.out.printf("Grupa %s nie znaleziona%n", groupCode);
        } else {
            System.out.println(group.getInfo());
        }
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
     * "Grupa pp-2022 nie istnieje"
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */
    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group group = university.findGroup(groupCode);
        if (group == null) {
            System.out.printf("Grupa %s nie istnieje%n", groupCode);
        } else {
            Student student = group.findStudent(studentIndex);
            if (student == null) {
                System.out.printf("Student o indeksie %d nie jest zapisany do grupy %s%n",
                                  studentIndex, groupCode);
            } else {
                Grade possibleGrade = university.findGradeForStudent(student, groupCode);
                if (possibleGrade != null) {
                    System.out.printf("Student o indeksie %d ma już wystawioną ocenę dla grupy %s%n", studentIndex, groupCode);
                } else {
                    university.addGrade(student, group, grade);
                }
            }
        }
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indesku studenta dla którego wyświetlić oceny
     */
    public void printGradesForStudent(int index) {
        Student student = university.findStudent(index);
        if (student != null) {
            System.out.println(student.getGradesList());
        } else {
            System.out.printf("Nie ma studenta o indeksie %d", index);
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */
    public void printGradesForGroup(String groupCode) {
        Group group = university.findGroup(groupCode);
        if (group != null) {
            Student[] students = group.getStudents();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null) {
                    Grade grade = university.findGradeForStudent(students[i], groupCode);
                    if (grade != null) {
                        String record = String.format(Locale.ENGLISH, "%s: %.1f%n", students[i].getInfo(), grade.getValue());
                        stringBuilder.append(record);
                    }
                }
            }
            System.out.println(stringBuilder);
        }
        System.out.printf("Grupa %s nie istnieje%n", groupCode);
    }

    /**
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */
    public void printAllStudents() {
        Student[] students = university.getStudents();
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.println(students[i].getInfo());
            }
        }
    }
}
