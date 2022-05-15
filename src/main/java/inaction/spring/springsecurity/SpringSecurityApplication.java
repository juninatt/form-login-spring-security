package inaction.spring.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple security application that uses Spring Security with a form-based login.
 * Generates a login page with link to login-form for unregistered users.
 * Login takes user to home page.
 * @author Petter Bergström
 */
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
