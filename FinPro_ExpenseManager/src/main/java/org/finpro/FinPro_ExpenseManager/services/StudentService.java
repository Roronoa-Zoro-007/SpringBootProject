package org.finpro.FinPro_ExpenseManager.services;

import java.util.List;

import org.finpro.FinPro_ExpenseManager.models.Student;
import org.finpro.FinPro_ExpenseManager.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository stdRep;
	
	public List<Student> viewAllStudents(){
		return stdRep.findAll();
	}
	
	public void addNewStudent(Student s) {
		stdRep.save(s);
	}

	public void deleteStudent(int id) {
		stdRep.deleteById(id);
	}
	
	public Student viewStudentById(int id) {
		return stdRep.findById(id).orElse(new Student());
	}
}
