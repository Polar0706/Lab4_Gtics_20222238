package com.tele.lab4_20222238.repository;

import com.tele.lab4_20222238.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
}
