package com.pfsprin.proyectofinalspring.repository;
import com.pfsprin.proyectofinalspring.Entidadess.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
