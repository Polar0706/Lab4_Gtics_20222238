package com.tele.lab4_20222238.repository;

import com.tele.lab4_20222238.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository <Doctor, Integer> {
}
