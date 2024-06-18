import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Student {
    private final String id;
    private final String firstName;
    private final String lastName;

    public Student(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();  // Генерація унікального ідентифікатора
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}

class StudentsGroup {
    private Student headman;
    private final List<Student> students;
    private final List<String> tasks;

    public StudentsGroup(Student headman) {
        if (headman == null) {
            throw new IllegalArgumentException("No group without Headman");
        }
        this.headman = headman;
        this.students = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.students.add(headman);  // Додавання старости до списку студентів
    }

    public Student getHeadman() {
        return headman;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);  // Захист від зовнішніх змін
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);  // Захист від зовнішніх змін
    }

    public void changeHeadman(Student newHeadman) {
        if (newHeadman == null || !students.contains(newHeadman)) {
            throw new IllegalArgumentException("New headman must be a student in the group");
        }
        this.headman = newHeadman;
    }

    public void addStudent(Student student) {
        if (student == null || students.contains(student)) {
            throw new IllegalArgumentException("Student is not exist or already in the group");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        if (student == null || student.equals(headman) || !students.contains(student)) {
            throw new IllegalArgumentException("Cannot remove headman or non-existent student");
        }
        students.remove(student);
    }

    public void addTask(String task) {
        if (task == null || task.trim().isEmpty()) {
            throw new IllegalArgumentException("Task cannot be null or empty");
        }
        tasks.add(task);
    }

    public void markTaskAsCompleted(Student student, String task) {
        if (student == null || !students.contains(student) || task == null || !tasks.contains(task)) {
            throw new IllegalArgumentException("Invalid student or task");
        }
        System.out.println("Task '" + task + "' marked as completed by " + student);
    }

    @Override
    public String toString() {
        return "Headman: " + headman + "\nStudents: " + students + "\nTasks: " + tasks;
    }
}

public class Solution {
    public static void main(String[] args) {
        Student student1 = new Student("Jack", "Daniels");
        Student student2 = new Student("Jim", "Beam");
        Student student3 = new Student("Johny", "Walker");

        StudentsGroup group = new StudentsGroup(student1);
        group.addStudent(student2);
        group.addStudent(student3);

        group.addTask("Study encapsulation");
        group.addTask("Read about inheritance");

        System.out.println(group);

        group.changeHeadman(student2);

        System.out.println("After changing headman:");
        System.out.println(group);

        group.markTaskAsCompleted(student3, "Study encapsulation");
    }
}
