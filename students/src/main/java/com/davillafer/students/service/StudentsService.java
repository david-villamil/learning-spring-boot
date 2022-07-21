package com.davillafer.students.service;

import com.davillafer.students.model.Student;
import com.davillafer.students.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> getStudents() {
        return studentsRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmail = studentsRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent())
            throw new IllegalStateException("Email already in use");
        studentsRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentsRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Student with id " + id + " doesn't exist")
        );

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail = studentsRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent())
                throw new IllegalStateException("Email already in use");
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long id) {
        boolean exists = studentsRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("Student with id " + id + " doesn't exist");
        studentsRepository.deleteById(id);
    }
}
