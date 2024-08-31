package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.request.ReviewCRUDDto;
import up.pdp.apprecipes.dto.ReviewDto;
import up.pdp.apprecipes.dto.request.ReviewEmotionDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<ReviewDto> save(ReviewCRUDDto reviewCRUDDto);
    List<ReviewDto> getAllByProductId(UUID id);
    void deleteById(UUID id);
    void emotion(ReviewEmotionDto reviewEmotionDto);
}
