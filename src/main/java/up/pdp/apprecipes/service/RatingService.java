package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.RatingDto;
import up.pdp.apprecipes.model.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    RatingDto save(RatingDto ratingDto);
    RatingDto getById(UUID id);
    List<Rating> getByRating(Double rating);
    List<RatingDto> getAll();
    List<RatingDto> getAllByRaterId(UUID raterId);
    void deleteById(UUID id);
}
