package up.pdp.apprecipes.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.enums.TimeFilter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProductSpecification {
    public static Specification<Product> byCategory(Category category) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (category == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("category"), category);
        };
    }

    public static Specification<Product> byRating(Rating rating) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (rating == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("rating"), rating);
        };
    }

    public static Specification<Product> filterTime(TimeFilter timeFilter) {
        LocalDateTime forFilter = LocalDateTime.of(2000, 1,1,12,0);
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (timeFilter == null) {
                return cb.conjunction();
            } else if (timeFilter == TimeFilter.OLDEST) {
                return cb.greaterThan(root.get("createdAt"), forFilter);
            }
                return cb.lessThan(root.get("createdAt"), LocalDateTime.now());
        };
    }
}
