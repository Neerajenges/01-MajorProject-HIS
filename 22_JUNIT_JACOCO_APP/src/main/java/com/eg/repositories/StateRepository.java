package com.eg.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entities.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Serializable> {

	public List<StateEntity> findByCountryId(Integer countryId);
}
