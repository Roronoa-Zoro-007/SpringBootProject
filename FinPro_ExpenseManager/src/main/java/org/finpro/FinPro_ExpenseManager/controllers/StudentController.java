package org.finpro.FinPro_ExpenseManager.controllers; 

import java.util.List;
import org.finpro.FinPro_ExpenseManager.models.Student;
import org.finpro.FinPro_ExpenseManager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService stdSer;

    // View All Students
    @GetMapping("/view")
    public String viewStudents(Model model) {
        List<Student> students = stdSer.viewAllStudents();
        model.addAttribute("studentList", students);
        return "students";  // Renders students.html
    }

    // Delete Student (Changed to DELETE)
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        stdSer.deleteStudent(id);
        return "redirect:/students/view";  // Redirect to updated list
    }

    @GetMapping("/add_student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student()); 
        return "addStudent";
    }

    @PostMapping("/add_student")
    public String addStudent(@ModelAttribute Student student) {
        stdSer.addNewStudent(student);
        return "redirect:/students/view";
    }

    @GetMapping("/view/{id}")
    public Student viewStudentById(@PathVariable int id) {
        return stdSer.viewStudentById(id);
    }
}
