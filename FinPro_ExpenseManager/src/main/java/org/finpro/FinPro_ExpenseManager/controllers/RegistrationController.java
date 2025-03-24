package org.finpro.FinPro_ExpenseManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.finpro.FinPro_ExpenseManager.models.User;
import org.finpro.FinPro_ExpenseManager.services.RegistrationService;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService regServ;  // Service to save the user to the database

    @Autowired
    private PasswordEncoder passwordEncoder;  // To encrypt the password

    // Display the registration page
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";  // Return the name of the registration page (register.html)
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        
        // Check if the user already exists
        if (regServ.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists.");
            return "register";  // Return to the registration page if the username already exists
        }

        // Encrypt the password before saving
        String encryptedPassword = passwordEncoder.encode(password);

        // Create a new user object
        User newUser = new User(username, encryptedPassword);

        // Save the new user
        regServ.save(newUser);

        return "redirect:/login";  // Redirect to the login page after successful registration
    }
}

