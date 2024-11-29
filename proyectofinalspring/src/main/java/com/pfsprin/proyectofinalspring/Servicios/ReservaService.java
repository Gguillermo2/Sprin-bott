package com.pfsprin.proyectofinalspring.Servicios;

import com.pfsprin.proyectofinalspring.Entidadess.Plan;
import com.pfsprin.proyectofinalspring.Entidadess.Reserva;
import com.pfsprin.proyectofinalspring.Entidadess.Usuario;
import com.pfsprin.proyectofinalspring.repository.ReservaRepository;
import com.pfsprin.proyectofinalspring.repository.UsuarioRepository;
import com.pfsprin.proyectofinalspring.repository.PlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PlanRepository planRepository;
    // Se obtienen todas las reservas
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }
    // Se obtiene la reserva por Id
    public Optional<Reserva> obtenerPorId(Integer id) {
        return reservaRepository.findById(id);
    }
    // Se crea una reserva
    public Reserva crearReserva(Reserva reserva) {

        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Verificar que el plan exista y esté disponible
        Plan plan = planRepository.findById(reserva.getPlan().getId())
                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado"));

        if (!plan.getDisponible() || plan.getCantidadActual() >= plan.getCantidadMaxima()) {
            throw new IllegalArgumentException("El plan no está disponible o ha alcanzado su capacidad máxima");
        }

        // Actualizar la cantidad actual del plan
        plan.setCantidadActual(plan.getCantidadActual() + 1);
        planRepository.save(plan);

        // Asignar usuario y plan a la reserva
        reserva.setUsuario(usuario);
        reserva.setPlan(plan);

        return reservaRepository.save(reserva);
    }

    // Se Elimina la reserva
    public Reserva actualizarReserva(Integer id, Reserva reservaDetalles) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setFecha(reservaDetalles.getFecha());
        reserva.setTipoReserva(reservaDetalles.getTipoReserva());
        reserva.setPagada(reservaDetalles.getPagada());
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Integer id) {
        reservaRepository.deleteById(id);
    }
}
