package com.eg.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {
	

}
