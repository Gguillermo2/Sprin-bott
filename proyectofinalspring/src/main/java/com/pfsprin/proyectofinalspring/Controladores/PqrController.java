package com.pfsprin.proyectofinalspring.Controladores;

import com.pfsprin.proyectofinalspring.Entidadess.Pqr;
import com.pfsprin.proyectofinalspring.Servicios.PqrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/pqr")
public class PqrController {

    @Autowired
    private PqrService pqrService;
    // Obtine las pqrs
    @GetMapping
    public List<Pqr> obtenerPqr() {
        return pqrService.obtenerTodas();
    }
    // obtiene una pqrs de un usario por id
    @GetMapping("/{id}")
    public Optional<Pqr> obtenerPqrPorId(@PathVariable Integer id) {
        return pqrService.obtenerPorId(id);
    }
    // crea la pqrs osea se envia
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pqr crearPqr(@RequestBody Pqr pqr) {
        return pqrService.crearPqr(pqr);
    }
    // se actualiza se responde
    @PutMapping("/{id}")
    public Pqr actualizarPqr(@PathVariable Integer id, @RequestBody Pqr pqr) {
        return pqrService.actualizarPqr(id, pqr);
    }
    // Se Elimina la pqrs
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPqr(@PathVariable Integer id) {
        pqrService.eliminarPqr(id);
    }
}
