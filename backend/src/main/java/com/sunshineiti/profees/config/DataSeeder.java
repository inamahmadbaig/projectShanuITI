package com.sunshineiti.profees.config;

import com.sunshineiti.profees.entity.User;
import com.sunshineiti.profees.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", passwordEncoder.encode("1234"), "ADMIN"));
            userRepository.save(new User("staff", passwordEncoder.encode("0000"), "STAFF"));
            System.out.println("Default users created: admin/1234, staff/0000");
        }
    }
}
