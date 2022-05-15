package spring.springsecurity.controllers;

import spring.springsecurity.entities.RegistrationForm;
import spring.springsecurity.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller class responsible for user registration.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    /**
     * UserRepository responsible for interacting with the user database and storing user credentials.
     * {@link UserRepository}
     */
    private final UserRepository userRepo;

    /**
     * Password encoder responsible for encoding and decoding user passwords.
     * @see PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder;


    /**
     * Constructor that initializes both fields.
     */
    public RegistrationController( UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method that finds and renders the view with name 'registration.html'.
     */
    @GetMapping
    public String registerForm() {
        return "registration";
    }

    /**
     * Method that saves a new user to the database with information given in the register form.
     * {@link RegistrationForm}
     */
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }
}