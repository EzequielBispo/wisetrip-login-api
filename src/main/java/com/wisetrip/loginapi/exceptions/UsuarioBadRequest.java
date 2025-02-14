package com.wisetrip.loginapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioBadRequest extends RuntimeException {
    public UsuarioBadRequest(int id) {
        super("Conta não encontrada");
    }
}
