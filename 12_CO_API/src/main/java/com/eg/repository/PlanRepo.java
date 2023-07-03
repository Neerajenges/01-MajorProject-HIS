package com.eg.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Serializable>{

}
