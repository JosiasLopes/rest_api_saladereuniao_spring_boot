package com.crud.saladereuniao.saladereuniao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    String message = "Usuario não encontrado";

    public UserNotFoundException(Long id){
        this.message = "Usuario" +id+ "não encontrado";
    }

    public UserNotFoundException(){

    }


}
