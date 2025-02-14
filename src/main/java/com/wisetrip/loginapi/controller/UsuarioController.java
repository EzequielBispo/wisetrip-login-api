package com.wisetrip.loginapi.controller;

import com.wisetrip.loginapi.exceptions.UsuarioNotFoundException;
import com.wisetrip.loginapi.model.Usuario;
import com.wisetrip.loginapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    // GET
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar usuario por ID",
            description = "Retorna pesquisa de usuario por ID")
    @ApiResponse(responseCode = "200", description = "Cadastro do usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    public EntityModel<Usuario> buscarId(@PathVariable int id) {
        Usuario usuario = service.getUsuario(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException(id);
        }
        EntityModel<Usuario> resource = EntityModel.of(usuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarId(id)).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).salvar(usuario)).withRel("salvar"));
        resource.add(linkTo(methodOn(UsuarioController.class).deletar(id)).withRel("deletar"));
        resource.add(linkTo(methodOn(UsuarioController.class).atualizar(id, usuario)).withRel("atualizar"));
        return resource;
    }

    // POST
    @PostMapping
    @Operation(
            summary = "Salvar cadastro usuario",
            description = "Salva cadastro do usuario")
    @ApiResponse(responseCode = "201", description = "Cadastro do usuario salvo")
    @ApiResponse(responseCode = "400", description = "Erro ao salvar usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<Usuario>> salvar(@RequestBody Usuario usuario) {
        Usuario savedUsuario = service.setUsuario(usuario);
        EntityModel<Usuario> resource = EntityModel.of(savedUsuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarId(savedUsuario.getId_usuario())).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).salvar(usuario)).withRel("salvar"));
        resource.add(linkTo(methodOn(UsuarioController.class).deletar(savedUsuario.getId_usuario())).withRel("deletar"));
        resource.add(linkTo(methodOn(UsuarioController.class).atualizar(savedUsuario.getId_usuario(), usuario)).withRel("atualizar"));
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar cadastro do usuario",
            description = "Deleta o cadastro do usuario")
    @ApiResponse(responseCode = "200", description = "Cadastro do usuario deletado")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletar(@PathVariable int id) {
        Usuario usuario = service.getUsuario(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException(id);
        }
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar cadastro do usuario",
            description = "Atualiza cadastro do usuario")
    @ApiResponse(responseCode = "200", description = "Cadastro do usuario atualizado")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    public ResponseEntity<EntityModel<Usuario>> atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario existente = service.getUsuario(id);
        if (existente == null) {
            throw new UsuarioNotFoundException(id);
        }
        usuario.setId_usuario(id);
        Usuario updatedUsuario = service.save(usuario);
        EntityModel<Usuario> resource = EntityModel.of(updatedUsuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarId(id)).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).salvar(usuario)).withRel("salvar"));
        resource.add(linkTo(methodOn(UsuarioController.class).deletar(id)).withRel("deletar"));
        resource.add(linkTo(methodOn(UsuarioController.class).atualizar(id, usuario)).withRel("atualizar"));
        return ResponseEntity.ok(resource);
    }


    static class OperationResponse {
        private Usuario usuario;
        private String message;

        public OperationResponse(Usuario usuario, String message) {
            this.usuario = usuario;
            this.message = message;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public String getMessage() {
            return message;
        }
    }
}
