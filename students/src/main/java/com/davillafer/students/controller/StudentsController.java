package com.davillafer.students.controller;

import com.davillafer.students.model.Student;
import com.davillafer.students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @GetMapping
    public List<Student> getStudents() {
        return studentsService.getStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentsService.addStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        studentsService.updateStudent(id, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentsService.deleteStudent(id);
    }
}
