package com.crud.saladereuniao.saladereuniao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException{
    String message = "Room não encontrada";

    public RoomNotFoundException(Long id){
        this.message = "Room" +id+ "não encontrada";
    }

    public RoomNotFoundException(){

    }


}
