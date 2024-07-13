package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
