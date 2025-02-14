package com.wisetrip.loginapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioDeleteException extends RuntimeException {
    public UsuarioDeleteException(int id) {
        super("Conta não encontrada");
    }
}
