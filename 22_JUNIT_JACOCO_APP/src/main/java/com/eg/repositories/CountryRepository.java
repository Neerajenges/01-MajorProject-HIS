package com.eg.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
