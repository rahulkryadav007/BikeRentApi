package com.app.bikerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bikerent.model.Bike;


public interface BikeRepository extends JpaRepository<Bike,Long> {

}
