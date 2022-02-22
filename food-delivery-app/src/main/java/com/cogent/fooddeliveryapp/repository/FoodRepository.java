package com.cogent.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cogent.fooddeliveryapp.dto.Food;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
