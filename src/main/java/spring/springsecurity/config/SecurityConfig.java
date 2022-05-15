package spring.springsecurity.config;

import spring.springsecurity.entities.AppUser;
import spring.springsecurity.handlers.CustomAuthenticationFailureHandler;
import spring.springsecurity.handlers.CustomAuthenticationSuccessHandler;
import spring.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class that contains the security logic for the application.
 */
@Configuration
public class SecurityConfig {

    /**
     * Injecting CustomAuthenticationSuccessHandler-bean.
     * {@link CustomAuthenticationSuccessHandler}
     */
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * Injecting CustomAuthenticationFailureHandler-bean.
     * {@link CustomAuthenticationFailureHandler}
     */
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * Bean that sets password encoder to BCryptPasswordEncoder.
     * @see BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param userRepo UserRepository responsible for interacting with the user database and storing user credentials.
     * @return Returns the user with the matching username, if exists.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            AppUser user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /**
     * Method that defines the security filter chain which intercepts requests
     * and makes sure the user has the right authorities to access it.
     * @see SecurityFilterChain
     * @see HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        return http
                .authorizeRequests()
                    .antMatchers("/home").access("hasRole('USER')")
                    .antMatchers("/", "/**").access("permitAll()")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                .and()
                    .logout()
                    .logoutSuccessUrl("/")

                .and()
                    .build();
    }
}