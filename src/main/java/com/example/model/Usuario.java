package com.example.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "el campo username no puede estar vacio")
    private String username;
    private String nombre;
    private String email;
    private String password;
    private Integer status;
    private Date fechaRegistro;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "usuarioPerfil",
               joinColumns = @JoinColumn(name="idUsuario"),
               inverseJoinColumns = @JoinColumn(name="idPerfil")
                )
    private List<Perfil> perfiles;

    public void agregar(Perfil tempPerfil){
        if  (perfiles == null){
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }

}
