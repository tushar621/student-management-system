package tushar.com;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student_Management_System {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n1. Add  2. View all  3. Search  4. Update marks  5. Delete  6. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: {
                        System.out.print("Enter id: ");
                        int id = sc.nextInt();
                        System.out.print("Enter name: ");
                        String name = sc.next();
                        System.out.print("Enter marks: ");
                        double marks = sc.nextDouble();
                        manager.addStudent(new Student(id, name, marks));
                        break;
                    }
                    case 2: {
                        manager.viewAllStudents();
                        break;
                    }
                    case 3: {
                        System.out.print("Enter id to search: ");
                        int searchId = sc.nextInt();
                        try {
                            manager.searchStudent(searchId);
                        } catch (StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        System.out.print("Enter id to update: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new marks: ");
                        double newMarks = sc.nextDouble();
                        try {
                            manager.updateMarks(updateId, newMarks);
                        } catch (StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("Enter id to delete: ");
                        int deleteId = sc.nextInt();
                        try {
                            manager.deleteStudent(deleteId);
                        } catch (StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 6: {
                        running = false;
                        System.out.println("Goodbye");
                        break;
                    }
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                sc.nextLine();
            }
        }
        sc.close();
    }
}   // <-- this closes Student_Management_System. This was the missing brace.

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentManager {
    private HashMap<Integer, Student> students = new HashMap<>();

    public void addStudent(Student s) {
        if (students.containsKey(s.getId())) {
            System.out.println("ID already exists");
        } else {
            students.put(s.getId(), s);
            System.out.println("Student added");
        }
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            for (Student s : students.values()) {
                System.out.println(s);
            }
        }
    }

    public void searchStudent(int id) throws StudentNotFoundException {
        if (students.containsKey(id)) {
            System.out.println(students.get(id));
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }

    public void updateMarks(int id, double marks) throws StudentNotFoundException {
        if (students.containsKey(id)) {
            Student s = students.get(id);
            s.setMarks(marks);
            System.out.println("Marks updated");
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student deleted");
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}