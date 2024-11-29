package com.pfsprin.proyectofinalspring.Controladores;

import com.pfsprin.proyectofinalspring.Entidadess.Reserva;
import com.pfsprin.proyectofinalspring.Entidadess.Usuario;
import com.pfsprin.proyectofinalspring.Servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerReservas() {
        return reservaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> obtenerReserva(@PathVariable Integer id) {
        return reservaService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        return reservaService.actualizarReserva(id, reserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarReserva(@PathVariable Integer id) {
        reservaService.eliminarReserva(id);
    }
}
