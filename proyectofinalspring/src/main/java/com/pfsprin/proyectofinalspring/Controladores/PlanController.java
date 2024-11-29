package com.pfsprin.proyectofinalspring.Controladores;
import com.pfsprin.proyectofinalspring.Entidadess.Plan;
import com.pfsprin.proyectofinalspring.Servicios.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    // Obtener todos los planes
    @GetMapping
    public ResponseEntity<List<Plan>> obtenerTodos() {
        List<Plan> planes = planService.obtenerTodos();
        return ResponseEntity.ok(planes);
    }

    // Obtener un plan por ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> obtenerPorId(@PathVariable Integer id) {
        Optional<Plan> planOpt = planService.obtenerPorId(id);
        return planOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener planes disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Plan>> obtenerPlanesDisponibles() {
        List<Plan> planesDisponibles = planService.obtenerPlanesDisponibles();
        return ResponseEntity.ok(planesDisponibles);
    }

    // Crear un nuevo plan
    @PostMapping
    public ResponseEntity<Plan> crearPlan(@RequestBody Plan plan) {
        Plan nuevoPlan = planService.guardarPlan(plan);
        return ResponseEntity.ok(nuevoPlan);
    }

    // Actualizar un plan existente
    @PutMapping("/{id}")
    public ResponseEntity<Plan> actualizarPlan(@PathVariable Integer id, @RequestBody Plan planDetalles) {
        Optional<Plan> planOpt = planService.obtenerPorId(id);
        if (planOpt.isPresent()) {
            Plan planExistente = planOpt.get();
            // Actualizar los campos del plan
            planExistente.setNombre(planDetalles.getNombre());
            planExistente.setDescripcion(planDetalles.getDescripcion());
            planExistente.setTipo(planDetalles.getTipo());
            planExistente.setCantidadMaxima(planDetalles.getCantidadMaxima());
            planExistente.setDisponible(planDetalles.getDisponible());

            Plan planActualizado = planService.guardarPlan(planExistente);
            return ResponseEntity.ok(planActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Integer id) {
        Optional<Plan> planOpt = planService.obtenerPorId(id);
        if (planOpt.isPresent()) {
            planService.eliminarPlan(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
