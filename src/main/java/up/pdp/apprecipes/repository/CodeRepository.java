package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Code;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {
    Optional<Code> findByEmail(String email);

    //    @Cacheable(value = "codeEntity", key = "#email")
    default Code getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new NotFoundException("email not found for verify code"));
    }

    //    @CachePut(value = "codeEntity", key = "#code.email")
    @Override
    Code save(Code code);

    //    @CacheEvict(value = "codeEntity", key = "#code.email")
    @Override
    void delete(Code code);
}