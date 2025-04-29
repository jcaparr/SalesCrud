package com.todocode.bazar.exception;

import com.todocode.bazar.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e) {
        ExceptionDto exceptionDTO = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<?> alreadyExist(AlreadyExist e) {
        ExceptionDto exceptionDTO = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        // Recolectamos los errores y los formateamos
        List<String> errors = result.getFieldErrors()
                .stream()
                .map(error -> "The field '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // Devolvemos un ResponseEntity con el código de estado 400 y los errores
        return new ResponseEntity<>(new ErrorResponse("Invalid data", errors), HttpStatus.BAD_REQUEST);
    }
}
