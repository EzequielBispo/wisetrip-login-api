package com.wisetrip.loginapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioNotPostException extends RuntimeException {
    public UsuarioNotPostException(int id) {
        super("Falta ou excesso de argumentos");
    }
}
