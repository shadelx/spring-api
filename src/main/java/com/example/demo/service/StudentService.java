package com.example.demo.service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	
	// @Autowired
    public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student)	 {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()){
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}
    public void deleteStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if (!exists){
			throw new IllegalStateException("student with Id "+studentId+" does not exists");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email){
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException(
				"student with id" + studentId + "does not exist"));

		if (name != null && !name.isEmpty() &&
			!Objects.equals(student.getName(), name)){
				student.setName(name);
			}

		if (email != null && !email.isEmpty() &&
			!Objects.equals(student.getEmail(), email)){
				Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
				if(studentOptional.isPresent()){
					throw new IllegalStateException("email taken");
				}
				student.setEmail(email);
			}
	}
}
