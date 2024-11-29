package com.pfsprin.proyectofinalspring.Servicios;

import com.pfsprin.proyectofinalspring.Entidadess.Pqr;
import com.pfsprin.proyectofinalspring.repository.PqrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PqrService {

    @Autowired
    private PqrRepository pqrRepository;
    // Obtine todos los pqrs
    public List<Pqr> obtenerTodas() {
        return pqrRepository.findAll();
    }
    // Obitneen el pqrs individual
    public Optional<Pqr> obtenerPorId(Integer id) {
        return pqrRepository.findById(id);
    }
    // se registran los pqrs
    public Pqr crearPqr(Pqr pqr) {
        return pqrRepository.save(pqr);
    }
    // se actualiza el pqrs
    public Pqr actualizarPqr(Integer id, Pqr pqrDetalles) {
        Pqr pqr = pqrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PQR no encontrada"));
        pqr.setDescripcion(pqrDetalles.getDescripcion());
        pqr.setCorreo(pqrDetalles.getCorreo());
        return pqrRepository.save(pqr);
    }
    // Se eleimina el pqrs
    public void eliminarPqr(Integer id) {
        pqrRepository.deleteById(id);
    }
}
