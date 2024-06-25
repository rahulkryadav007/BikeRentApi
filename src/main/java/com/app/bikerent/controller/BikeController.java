package com.app.bikerent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bikerent.model.Bike;
import com.app.bikerent.repository.BikeRepository;
import com.app.bikerent.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

	@Autowired
	private BikeRepository bikeRepository;
	
	@GetMapping
	public List<Bike> getAllBikes() {
		return bikeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Bike getBikeById(@PathVariable Long id) {
		return bikeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
	}
		
	@PostMapping
	public Bike createBike(@RequestBody Bike bike) {
		return bikeRepository.save(bike);	
	}
	
	@PutMapping("/{id}")
	public Bike updateBike(@PathVariable Long id, @RequestBody Bike bikeDetails) {
		Bike bike = bikeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
		bike.setModel(bikeDetails.getModel());
		bike.setColor(bikeDetails.getColor());
		bike.setRented(bikeDetails.getRented());
		return bikeRepository.save(bike);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBike(@PathVariable Long id) {
		Bike bike = bikeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bike not found with id " + id));
		bikeRepository.delete(bike);
		return ResponseEntity.ok().build();
	}
}
