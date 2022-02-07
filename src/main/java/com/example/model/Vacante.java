package com.example.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Vacantes")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "el campo nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "el campo descripcion no puede estar vacio")
    private String descripcion;
    private Date fecha;
    private Double salario;
    private Integer destacado;
    private String imagen="no-imagen.jpg";
    private String status;
    private String detalles;
    //@Transient
    @ManyToOne
    @JoinColumn(name="idCategoria")
    private Categoria categoria;

    public void reset() {
        this.imagen=null;
    }
}
