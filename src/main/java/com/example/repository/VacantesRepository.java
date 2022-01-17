package com.example.repository;

import com.example.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer>{

        List<Vacante> findByStatus(String status);

        List<Vacante> findByDestacadoAndStatusOrderByIdDesc(int destacado, String status);

        List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);

        List<Vacante> findByStatusIn(String [] status);
}
