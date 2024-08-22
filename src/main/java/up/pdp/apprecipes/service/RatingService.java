package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.RatingDto;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    RatingDto save(RatingDto ratingDto);
    RatingDto getById(UUID id);
    List<RatingDto> getAll();
    List<RatingDto> getAllByRaterId(UUID raterId);
    void deleteById(UUID id);
}
