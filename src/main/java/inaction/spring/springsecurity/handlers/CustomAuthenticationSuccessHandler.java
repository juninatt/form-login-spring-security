package inaction.spring.springsecurity.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A handler class that describes what should happen user authorization is successful.
 * @see AuthenticationSuccessHandler
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * A method that redirects the user to home-page {@link /home.jpg} if the user has 'read' authority,
     * otherwise it redirects user to error-page {@link /error.jpeg}.
     * @param httpServletRequest The request made by the servlet.
     * @param httpServletResponse The response from the servlet.
     * @param authentication The authentication object.
     * @throws IOException Handles system input and output exceptions.
     */
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication)
            throws IOException {
        var authorities = authentication.getAuthorities();
        var authority =
                authorities.stream()
                        .filter(a -> a.getAuthority().equals("read"))
                        .findFirst();
        if (authority.isPresent()) {
            httpServletResponse
                    .sendRedirect("/home");
        } else {
            httpServletResponse
                    .sendRedirect("/error");
        }
    }
}