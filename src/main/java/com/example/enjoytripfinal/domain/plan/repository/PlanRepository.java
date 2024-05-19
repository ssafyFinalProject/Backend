package com.example.enjoytripfinal.domain.plan.repository;

import com.example.enjoytripfinal.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
}
