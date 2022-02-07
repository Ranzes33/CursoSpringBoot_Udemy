package com.example.service;

import com.example.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoriasService {
    void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
    void eliminar(Integer idCategoria);
    Page<Categoria> buscarTodas(Pageable pagea);
}
