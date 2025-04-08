package com.wisetrip.loginapi.repository;

import com.wisetrip.loginapi.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List findAll();
    Usuario findById(int id);
    Usuario save(Usuario usuario);
}