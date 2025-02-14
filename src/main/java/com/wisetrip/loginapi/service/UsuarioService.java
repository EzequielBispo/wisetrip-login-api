package com.wisetrip.loginapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.wisetrip.loginapi.model.Usuario;
import com.wisetrip.loginapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  
    @Autowired
    private UsuarioRepository repository;

    public Usuario getUsuario(int id) {
        return repository.findById(id);
    }

    public Usuario setUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario deleteUsuario(int id) {
        Usuario usuario = repository.findById(id);
        repository.delete(usuario);
        return usuario;
        
    }
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}
