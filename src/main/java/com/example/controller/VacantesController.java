package com.example.controller;

import com.example.model.Vacante;
import com.example.service.ICategoriasService;
import com.example.service.IVacantesService;
import com.example.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class  VacantesController {

    @Value("${empleos.ruta.imagenes}")
    private String ruta;

    @Autowired
    private IVacantesService serviceVacantes;

    @Autowired
    //@Qualifier("categoriasServiceJpa")
    private ICategoriasService serviceCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model, Vacante vacante){
        List<Vacante> lista = serviceVacantes.buscarTodas();
        model.addAttribute("vacantes", lista);
        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model){
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
                          @RequestParam("archivoImagen") MultipartFile multiPart){
        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrio un error = " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multiPart.isEmpty()) {
            //String ruta = "D:/empleos/img-vacantes/";
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }

        serviceVacantes.guardar(vacante);
        attributes.addFlashAttribute("msg","Registro Guardado");
        System.out.println("Vacante = " + vacante);
        return "redirect:/vacantes/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes){
        System.out.println("Borrando vacante con id= " + idVacante);
        attributes.addFlashAttribute("msg", "La vacante fue eliminada");
        serviceVacantes.eliminar(idVacante);
        return "redirect:/vacantes/index";
    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idVacante, Model model){
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";
    }
    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("categorias", serviceCategorias.buscarTodas());
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model){
        Vacante vacante = serviceVacantes.buscarPorId(idVacante);
        System.out.println("vacante = " + vacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }


}
