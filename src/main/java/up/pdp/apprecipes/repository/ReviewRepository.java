package up.pdp.apprecipes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Review;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @NonNull
    default Review getById(@NonNull UUID id) {
        return findById(id).orElseThrow(()-> NotFoundException.errorById("Review", id));
    }
    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.likes = r.likes + 1 WHERE r.id = :reviewId")
    void incrementLike(@Param("reviewId") UUID reviewId);

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.dislikes = r.dislikes + 1 WHERE r.id = :reviewId")
    void incrementDislike(@Param("reviewId") UUID reviewId);
}
