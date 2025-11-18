package com.example.insurai.config;

import com.example.insurai.entity.Role;
import com.example.insurai.entity.User;
import com.example.insurai.repository.UserRepository;
import com.example.insurai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setName("Admin User");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(Set.of(Role.ADMIN));
            userRepository.save(admin);
            System.out.println("Default admin user created: admin@gmail.com / 1234");
        }
    }
}
