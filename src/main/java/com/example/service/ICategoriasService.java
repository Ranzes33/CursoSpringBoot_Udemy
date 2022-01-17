package com.example.service;

import com.example.model.Categoria;

import java.util.List;


public interface ICategoriasService {
    void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
}
