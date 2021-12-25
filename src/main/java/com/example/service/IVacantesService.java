package com.example.service;
import java.util.List;
import com.example.model.Vacante;

public interface IVacantesService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    void guardar(Vacante vacante);
}
