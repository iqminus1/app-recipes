package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.RatingCrudDto;
import up.pdp.apprecipes.model.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    Rating save(RatingCrudDto ratingDto);
    Rating getById(UUID id);
    List<Rating> getAll();
    List<Rating> getAllByRaterId(UUID raterId);
    void deleteById(UUID id);
}
