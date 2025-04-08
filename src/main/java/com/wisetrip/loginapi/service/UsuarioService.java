package com.wisetrip.loginapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.wisetrip.loginapi.model.Usuario;
import com.wisetrip.loginapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  
    @Autowired
    private UsuarioRepository repository;
    private PasswordEncoder passwordEncoder;
    
    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Usuario getUsuario(int id) {
        return repository.findById(id);
    }

    public Usuario setUsuario(Usuario usuario) {
        usuario.setDs_senha(passwordEncoder.encode(usuario.getDs_senha()));
        return repository.save(usuario);
    }

    public Usuario deleteUsuario(int id) {
        Usuario usuario = repository.findById(id);
        repository.delete(usuario);
        return usuario;
        
    }
    public Usuario save(Usuario usuario) {
        usuario.setDs_senha(passwordEncoder.encode(usuario.getDs_senha()));
        return repository.save(usuario);
    }
}
