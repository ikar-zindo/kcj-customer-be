package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {

   Optional<List<Review>> findAllByRestaurantId(Long restaurantId);
}
