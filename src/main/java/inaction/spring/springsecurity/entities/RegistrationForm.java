package inaction.spring.springsecurity.entities;


import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A class representing the registration form for registering users.
 */
@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;


    /**
     * Method that takes the information from the register form
     * and creates a new user after encoding the password.
     */
    public AppUser toUser(PasswordEncoder passwordEncoder) {
        return new AppUser(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }

}