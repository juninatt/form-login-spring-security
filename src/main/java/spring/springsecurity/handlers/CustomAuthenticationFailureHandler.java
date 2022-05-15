package spring.springsecurity.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A handler class that describes what should happen if authentication of a user fails.
 * @see AuthenticationFailureHandler
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * A method that handles customization of failed authentication attempts.
     * Redirects user to error-page {@link /error.jpeg}
     * @param httpServletRequest The request made by the servlet.
     * @param httpServletResponse The response from the servlet.
     * @param exception The authentication object.
     * @throws IOException Handles system input and output exceptions.
     * @see IOException
     */
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException exception) throws IOException {
        httpServletResponse
                .sendRedirect("/error");
    }
}