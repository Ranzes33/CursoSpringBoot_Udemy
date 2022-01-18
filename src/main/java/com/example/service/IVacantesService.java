package com.example.service;
import com.example.model.Vacante;

import java.util.List;

public interface IVacantesService {
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    void guardar(Vacante vacante);
    List<Vacante> buscarDestacadas();
    void eliminar(Integer idVacante);
}
