package com.example.controller;

import com.example.model.Usuario;
import com.example.repository.UsuariosRepository;
import com.example.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService serviceUsuarios;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        return "usuarios/listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id")int idUsuario, RedirectAttributes attributes){
        System.out.println("Borrando usuario con id = " +idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado");
        serviceUsuarios.eliminar(idUsuario);
        return "redirect:/usuarios/index";
    }

    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("usuarios", serviceUsuarios.buscarTodos());
    }
}
