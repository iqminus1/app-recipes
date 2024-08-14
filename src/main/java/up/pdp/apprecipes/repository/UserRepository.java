package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    //    @Cacheable(value = "userEntity", key = "#username")
    default User getByUsername(String username) {
        return findByUsername(username).orElseThrow(() -> new NotFoundException("User not found by username -> " + username));
    }

    @Override
//    @CachePut(value = "userEntity", key = "#result.username")
    User save(User user);

    @Override
//    @CacheEvict(value = "userEntity", key = "#user.username")
    void delete(User user);
}