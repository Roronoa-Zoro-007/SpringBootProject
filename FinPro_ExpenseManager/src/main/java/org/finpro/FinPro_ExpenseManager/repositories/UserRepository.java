package org.finpro.FinPro_ExpenseManager.repositories;

import java.util.Optional;

import org.finpro.FinPro_ExpenseManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
