package com.mariaClara.SistemaBicicletario.exception;


import com.mariaClara.SistemaBicicletario.dto.ErroDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroDto> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroDto erro = new ErroDto("RECURSO_NAO_ENCONTRADO", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDto> tratarValidacao(MethodArgumentNotValidException ex) {
        var mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErroDto("DADOS_INVALIDOS", mensagem));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroDto> tratarValidacaoManual(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErroDto("DADOS_INVALIDOS", ex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDto> tratarErroGeral(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroDto("ERRO_INTERNO", "Erro inesperado."));
    }
}
