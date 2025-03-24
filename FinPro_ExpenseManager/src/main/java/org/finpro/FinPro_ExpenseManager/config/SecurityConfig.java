package org.finpro.FinPro_ExpenseManager.config;

import org.finpro.FinPro_ExpenseManager.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF (optional, not recommended for production)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/register").permitAll() // Allow access to the login and registration pages
                    .anyRequest().authenticated() // All other endpoints require authentication
                )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page (optional)
                .defaultSuccessUrl("/") // Redirect to root ("/") after successful login
                .permitAll() // Allow access to the login page for everyone
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Custom logout URL
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication
            .userDetailsService(userDetailsService); // Use custom UserDetailsService

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); // Use BCrypt with strength 10
    }
}