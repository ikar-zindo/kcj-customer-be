package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
