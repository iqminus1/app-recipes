package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.ReviewCRUDDto;
import up.pdp.apprecipes.dto.ReviewDto;
import up.pdp.apprecipes.dto.request.ReviewEmotionDto;
import up.pdp.apprecipes.exceptions.InvalidDataException;
import up.pdp.apprecipes.exceptions.NotFoundException;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Review;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.model.enums.Emotion;
import up.pdp.apprecipes.repository.ProductRepository;
import up.pdp.apprecipes.repository.ReviewRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ReviewDto> save(ReviewCRUDDto reviewCRUDDto) {
        User author = userRepository.getById(reviewCRUDDto.getAuthorId());
        Product product = productRepository.getById(reviewCRUDDto.getProductId());

        Review reviewBuilder = Review.builder()
                .author(author)
                .text(reviewCRUDDto.getText())
                .time(LocalDateTime.now())
                .build();
        product.getReview().add(reviewBuilder);
        reviewRepository.save(reviewBuilder);
        productRepository.save(product);

        return ReviewDto.of(product);
    }

    @Override
    public List<ReviewDto> getAllByProductId(UUID productId) {
        if (productRepository.findById(productId).isPresent())
            return ReviewDto.of(productRepository.findById(productId).get());
        else
            throw NotFoundException.errorById("Product", productId);
    }

    @Override
    public void deleteById(UUID id) {
        reviewRepository.delete(reviewRepository.getById(id));
    }

    @Override
    public void emotion(ReviewEmotionDto reviewEmotionDto) {
        reviewRepository.getById(reviewEmotionDto.getId());

        if (reviewEmotionDto.getEmotion() == Emotion.LIKE)
            reviewRepository.incrementLike(reviewEmotionDto.getId());
        else if (reviewEmotionDto.getEmotion() == Emotion.DISLIKE)
            reviewRepository.incrementDislike(reviewEmotionDto.getId());
        else
            throw new InvalidDataException("Review");
    }
}
