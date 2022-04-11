package inaction.spring.springsecurity.Repositories;

import inaction.spring.springsecurity.Enteties.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}