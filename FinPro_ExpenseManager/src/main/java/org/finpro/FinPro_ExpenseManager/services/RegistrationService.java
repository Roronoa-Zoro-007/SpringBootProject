package org.finpro.FinPro_ExpenseManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.finpro.FinPro_ExpenseManager.models.User;
import org.finpro.FinPro_ExpenseManager.repositories.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    // Check if the user already exists by username
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Save a new user
    public void save(User user) {
        userRepository.save(user);
    }
}
