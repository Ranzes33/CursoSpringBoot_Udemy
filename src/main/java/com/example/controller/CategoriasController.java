package com.example.controller;

import com.example.model.Categoria;
import com.example.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

    @Autowired
    //@Qualifier("categoriasServiceJpa")
    private ICategoriasService serviceCategorias;

     @GetMapping("/index")
    //@RequestMapping(value="/index", method= RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Categoria> lista = serviceCategorias.buscarTodas();
        model.addAttribute("categorias", lista);
        return "categorias/listCategoria";
    }
    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
         Page<Categoria> lista = serviceCategorias.buscarTodas(page);
         model.addAttribute("categorias", lista);
         return "categorias/listCategoria";
    }
    @GetMapping("/create")
    //@RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

     @PostMapping("/save")
    //@RequestMapping(value="/save", method=RequestMethod.POST)
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            System.out.println("Existieron errores al guardar");
            return "categorias/formCategoria";
        }
        serviceCategorias.guardar(categoria);
        attributes.addFlashAttribute("msg", "Los datos de categoria fueron guardados");
        return "redirect:/categorias/index";
    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idCategoria, Model model) {
        Categoria categoria = serviceCategorias.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
        // Eliminamos la categoria.
        serviceCategorias.eliminar(idCategoria);
        attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
        return "redirect:/categorias/index";
    }

}
