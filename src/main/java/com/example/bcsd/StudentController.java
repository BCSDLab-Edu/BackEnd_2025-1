package com.example.bcsd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    // Create
    @PostMapping
    public String addStudent(@RequestBody Student student) {
        service.AddStudent(student);
        return "학생이 추가되었습니다.";
    }

    // Read all
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // Read one
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudentByid(id);
    }

    // Update
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student updated) {
        service.UpdateStudent(id, updated);
        return "학생 정보가 수정되었습니다.";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "학생이 삭제되었습니다.";
    }
}
