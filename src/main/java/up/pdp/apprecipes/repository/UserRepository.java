package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @NonNull
    default User getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("User", id));
    }
    Optional<User> findByEmail(String email);

    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new NotFoundException("User not found by email -> " + email));
    }

    boolean existsByEmailAndDeletedFalse(String email);
}