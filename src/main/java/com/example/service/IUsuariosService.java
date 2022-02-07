package com.example.service;

import com.example.model.Usuario;

import java.util.List;

public interface IUsuariosService {
    void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
    List<Usuario> buscarTodos();
}
