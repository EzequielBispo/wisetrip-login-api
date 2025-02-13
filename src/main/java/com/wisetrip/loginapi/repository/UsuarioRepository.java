package com.wisetrip.loginapi.repository;

import com.wisetrip.loginapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findById(int id);
    Usuario save(Usuario usuario);
}