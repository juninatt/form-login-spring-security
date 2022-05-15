package spring.springsecurity.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * The entity that represent the app user. Stores security and contact information.
 * @see UserDetails
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(force=true)
@RequiredArgsConstructor
public class AppUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * Automatically generated id value.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * The value used to find the user in the database for security purposes.
     */
    private final String username;

    /**
     * Stores the user password which is used to log in.
     */
    private final String password;


    /**
     * User contact information
     */
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;

    /**
     * @return Returns the authority 'ROLE_USER' inside a collection.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
