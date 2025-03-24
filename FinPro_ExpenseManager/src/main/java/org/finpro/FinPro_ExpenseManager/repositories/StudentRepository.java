package org.finpro.FinPro_ExpenseManager.repositories;

import org.finpro.FinPro_ExpenseManager.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
