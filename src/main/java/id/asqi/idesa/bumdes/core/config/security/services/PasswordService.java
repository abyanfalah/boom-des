package id.asqi.idesa.bumdes.core.config.security.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    public boolean verifyPassword(String plainTextPassword, String encodedPassword) {
        return passwordEncoder.matches(plainTextPassword, encodedPassword);
    }
}