package user.user_service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("your_password");
        System.out.println(encodedPassword);
    }
}
