package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
