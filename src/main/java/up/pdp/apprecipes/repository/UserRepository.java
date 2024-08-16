package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new NotFoundException("User not found by username -> " + username));
    }
}