package com.example.db;

import com.example.model.Vacante;
import com.example.repository.VacantesRepository;
import com.example.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {

    @Autowired
    VacantesRepository vacantesRepo;

    @Override
    public List<Vacante> buscarTodas() {
       return vacantesRepo.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {
        Optional<Vacante> optional= vacantesRepo.findById(idVacante);
        if(optional.isPresent()) return optional.get();
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepo.save(vacante);
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return vacantesRepo.findByDestacadoAndStatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {
        vacantesRepo.deleteById(idVacante);
    }
}
