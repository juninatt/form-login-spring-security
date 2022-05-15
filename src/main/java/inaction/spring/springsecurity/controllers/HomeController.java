package inaction.spring.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A controller class with one method which directs the user to the homepage.
 */
@Controller
public class HomeController {

    /**
     * Method that finds and renders the view with name 'home.html'.
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}