package com.example.bcsd;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private Map<Integer, Student> studentData = new HashMap<>();

    // Create
    public void AddStudent(Student student) {
        studentData.put(student.getId(), student);
    }

    // read all
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentData.values());
    }

    // read one
    public Student getStudentByid(int id) {
        return studentData.get(id);
    }

    // Update
    public void UpdateStudent(int id, Student updated) {
        studentData.put(id, updated);
    }

    // Delete
    public void deleteStudent(int id) {
        studentData.remove(id);
    }

}
