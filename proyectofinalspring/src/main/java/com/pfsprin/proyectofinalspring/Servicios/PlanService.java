package com.pfsprin.proyectofinalspring.Servicios;

import com.pfsprin.proyectofinalspring.Entidadess.Plan;
import com.pfsprin.proyectofinalspring.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    // Obtener todos los planes
    public List<Plan> obtenerTodos() {
        return planRepository.findAll();
    }

    // Obtener un plan por su ID
    public Optional<Plan> obtenerPorId(Integer id) {
        return planRepository.findById(id);
    }

    // Obtener todos los planes disponibles
    public List<Plan> obtenerPlanesDisponibles() {
        return planRepository.findAll().stream()
                .filter(Plan::getDisponible)
                .toList();
    }

    // Guardar un plan (crear o actualizar)
    public Plan guardarPlan(Plan plan) {
        return planRepository.save(plan);
    }

    // Eliminar un plan por su ID
    public void eliminarPlan(Integer id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
        } else {
            throw new RuntimeException("Plan no encontrado con ID: " + id);
        }
    }

}
