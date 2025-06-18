package com.mariaClara.SistemaBicicletario.controllers;

import com.mariaClara.SistemaBicicletario.dto.BicicletaDto;
import com.mariaClara.SistemaBicicletario.dto.ErroDto;
import com.mariaClara.SistemaBicicletario.dto.NovaBicicletaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.services.BicicletaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {
    private final BicicletaService bicicletaService;

    public BicicletaController(BicicletaService bicicletaService){
        this.bicicletaService = bicicletaService;
    }

    @PostMapping
    public ResponseEntity<BicicletaDto> cadastrarBicicleta(@RequestBody @Valid NovaBicicletaDto novaBicicleta){

        BicicletaDto bicicletaCadastrada = bicicletaService.cadastrar(novaBicicleta);
            return ResponseEntity.status(HttpStatus.OK).body(bicicletaCadastrada);
    }

    @GetMapping("/")
    public ResponseEntity<List<BicicletaDto>>listarBicicletas(){
        List<BicicletaDto> bicicletas = bicicletaService.listarBicicletas();
        return ResponseEntity.ok(bicicletas);
    }


    @GetMapping("/{idBicicleta}")
    public ResponseEntity<BicicletaDto> buscarBicicletaPorId(@PathVariable Long idBicicleta){
        BicicletaDto resultado = bicicletaService.buscaPorId(idBicicleta);

        if (resultado == null){
           throw new RecursoNaoEncontradoException("Bicicleta com Id " +  idBicicleta + " nao encontrada");
        }
        return ResponseEntity.ok(resultado);
    }


    @PutMapping("/{idBicicleta}")
    public ResponseEntity<BicicletaDto> editarBicicleta(@PathVariable Long idBicicleta, @RequestBody @Valid NovaBicicletaDto novaBicicleta){
        BicicletaDto bicicletaEditada = bicicletaService.atualizarBicicleta(idBicicleta, novaBicicleta);
        if (bicicletaEditada == null){
            throw new RecursoNaoEncontradoException("Bicicleta com Id " +  idBicicleta + " nao encontrada");
        }

        return ResponseEntity.ok(bicicletaEditada);
    }

    @DeleteMapping("/{idBicicleta}")
    public ResponseEntity<Void> removerBicicleta(@PathVariable Long idBicicleta){
        bicicletaService.removeBicicleta(idBicicleta);
        return ResponseEntity.ok().build();
    }




}
