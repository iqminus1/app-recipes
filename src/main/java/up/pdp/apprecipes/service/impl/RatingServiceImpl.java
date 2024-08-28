package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.RatingDto;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.repository.RatingRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.service.RatingService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public RatingDto save(RatingDto ratingDto) {
        User rater = userRepository.getById(ratingDto.getRaterId());
        Product product = productRepository.getById(ratingDto.getProductId());
        Double overallRating = product.getOverallRating();
        int counter = 0;
        for (Rating rating : product.getRatings()){
            counter++;
            overallRating += rating.getRating();
        }
        product.setOverallRating(overallRating / counter);
        productRepository.save(product);
        Rating rating = Rating.builder()
                .rater(rater)
                .product(product)
                .rating(ratingDto.getRating())
                .build();
        return new RatingDto(ratingRepository.save(rating));
    }

    @Override
    public RatingDto getById(UUID id) {
        return new RatingDto(ratingRepository.getById(id));
    }

    @Override
    public List<Rating> getByRating(Double rating) {
        return ratingRepository.findAllByRating(rating);
    }

    @Override
    public List<RatingDto> getAll() {
        return ratingRepository.findAll()
                .stream()
                .map(RatingDto::new)
                .toList();
    }

    @Override
    public List<RatingDto> getAllByRaterId(UUID raterId) {
        return ratingRepository.findAllRatingsByRaterId(raterId)
                .stream()
                .map(RatingDto::new)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        ratingRepository.delete(ratingRepository.getById(id));
    }
}
