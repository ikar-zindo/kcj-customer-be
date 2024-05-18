package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
