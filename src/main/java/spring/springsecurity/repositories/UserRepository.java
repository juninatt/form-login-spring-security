package spring.springsecurity.repositories;

import spring.springsecurity.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface responsible for interacting with the user database and storing user credentials.
 * @see CrudRepository
 */
public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}