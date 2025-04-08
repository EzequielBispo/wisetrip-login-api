package com.wisetrip.loginapi.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_usuario;
    
    @NotBlank
    private String nm_usuario;

    @NotBlank
    private String sb_usuario;

    @NotBlank
    private String ds_email;

    private String ds_senha;
}