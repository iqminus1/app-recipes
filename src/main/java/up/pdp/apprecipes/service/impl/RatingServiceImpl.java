package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.RatingCrudDto;
import up.pdp.apprecipes.exceptions.NotFoundException;
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
    public Rating save(RatingCrudDto ratingDto) {
        User rater = userRepository.findById(ratingDto.getRaterId())
                .orElseThrow(() -> new NotFoundException("Rating"));

        Product product = productRepository.findById(ratingDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product"));

        Rating rating = Rating.builder()
                .rater(rater)
                .product(product)
                .rating(ratingDto.getRating())
                .build();

        return ratingRepository.save(rating);
    }

    @Override
    public Rating getById(UUID id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating"));
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByRaterId(UUID raterId) {
        return ratingRepository.findAllRatingsByRaterId(raterId);
    }

    @Override
    public void deleteById(UUID id) {
        ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating"));
        ratingRepository.deleteById(id);
    }
}
