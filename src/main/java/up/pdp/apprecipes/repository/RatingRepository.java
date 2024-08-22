package up.pdp.apprecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Rating;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    @NonNull
    default Rating getById(@NonNull UUID id) {
        return findById(id).orElseThrow(() -> NotFoundException.errorById("Rating", id));
    }

    List<Rating> findAllRatingsByRaterId(UUID userId);
}
