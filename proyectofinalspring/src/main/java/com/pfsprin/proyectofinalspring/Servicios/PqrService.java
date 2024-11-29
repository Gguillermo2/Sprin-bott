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

    public List<Pqr> obtenerTodas() {
        return pqrRepository.findAll();
    }

    public Optional<Pqr> obtenerPorId(Integer id) {
        return pqrRepository.findById(id);
    }

    public Pqr crearPqr(Pqr pqr) {
        return pqrRepository.save(pqr);
    }

    public Pqr actualizarPqr(Integer id, Pqr pqrDetalles) {
        Pqr pqr = pqrRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PQR no encontrada"));
        pqr.setDescripcion(pqrDetalles.getDescripcion());
        pqr.setCorreo(pqrDetalles.getCorreo());
        return pqrRepository.save(pqr);
    }

    public void eliminarPqr(Integer id) {
        pqrRepository.deleteById(id);
    }
}
