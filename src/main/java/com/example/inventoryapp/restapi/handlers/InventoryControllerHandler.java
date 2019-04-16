package com.example.inventoryapp.restapi.handlers;

import com.example.inventoryapp.exceptions.ObjectNotFoundException;
import com.example.inventoryapp.restapi.controllers.InventoryController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = InventoryController.class)
public class InventoryControllerHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Throwable ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(Throwable ex) {
        return ex.getMessage();
    }
}
