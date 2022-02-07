package com.example.service;

import com.example.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriasService{

    private List<Categoria> lista = null;

    public CategoriaServiceImpl(){
        lista = new LinkedList<Categoria>();
        //categoria1
        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setNombre("Contabilidad");
        categoria1.setDescripcion("Descripcion de las categorias de la Contabilidad");

        //categoria2
        Categoria categoria2 = new Categoria();
        categoria2.setId(2);
        categoria2.setNombre("Ventas");
        categoria2.setDescripcion("Trabajos relacionados con Ventas");

        //categoria3
        Categoria categoria3 = new Categoria();
        categoria3.setId(3);
        categoria3.setNombre("Comunicaciones");
        categoria3.setDescripcion("Trabajos relacionados con Comunicaciones");

        //categoria4
        Categoria categoria4 = new Categoria();
        categoria4.setId(4);
        categoria4.setNombre("Arquitectura");
        categoria4.setDescripcion("Trabajos de Arquitectura");

        //categoria 5
        Categoria categoria5 = new Categoria();
        categoria5.setId(5);
        categoria5.setNombre("Educacion");
        categoria5.setDescripcion("Maestros, tutores, etc");

        //categoria 6
        Categoria categoria6 = new Categoria();
        categoria6.setId(6);
        categoria6.setNombre("Desarrollo de Software");
        categoria6.setDescripcion("Trabajo para programadores");

        lista.add(categoria1);
        lista.add(categoria2);
        lista.add(categoria3);
        lista.add(categoria4);
        lista.add(categoria5);
        lista.add(categoria6);
    }

    @Override
    public void guardar(Categoria categoria) {
        lista.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return lista;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for(Categoria cat: lista){
            if(cat.getId() == idCategoria){
                return cat;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer idCategoria) {

    }

    @Override
    public Page<Categoria> buscarTodas(Pageable pageable) {
        return null;
    }
}
