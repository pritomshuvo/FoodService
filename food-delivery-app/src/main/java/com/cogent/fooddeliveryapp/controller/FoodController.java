package com.cogent.fooddeliveryapp.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.fooddeliveryapp.IdNotFoundException;
import com.cogent.fooddeliveryapp.dto.Food;
import com.cogent.fooddeliveryapp.repository.FoodRepository;

@RestController
@RequestMapping("/food")
@Validated
public class FoodController {
	
	@Autowired
	FoodRepository foodRepository;
	
	@PostMapping(value = "/")
	public ResponseEntity<?> createFood(@Valid @RequestBody Food food) {
		//TODO: process POST request
		Food food2=foodRepository.save(food);
		return ResponseEntity.status(201).body(food2);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") @Min(1) long id){
		Food food=foodRepository.findById(id).orElseThrow(()->new IdNotFoundException("id not found"));
		return ResponseEntity.ok(food);
	}
	
	@GetMapping("/all/desc")
	public ResponseEntity<?> getAllDescOrder(){
		
		List<Food> list=foodRepository.findAll();
		Collections.sort(list,(a,b)->b.getId().compareTo(a.getId()));
		return ResponseEntity.status(200).body(list);
		
		
		
	}

}
