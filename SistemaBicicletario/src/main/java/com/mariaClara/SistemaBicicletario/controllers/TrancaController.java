package com.mariaClara.SistemaBicicletario.controllers;

import com.mariaClara.SistemaBicicletario.dto.ErroDto;
import com.mariaClara.SistemaBicicletario.dto.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.dto.TrancaDto;
import com.mariaClara.SistemaBicicletario.services.TrancaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trancas")
public class TrancaController {
    private final TrancaService trancaService;

    public TrancaController(TrancaService trancaService) {
        this.trancaService = trancaService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTranca(@RequestBody @Valid NovaTrancaDto novaTranca){
        try{
            TrancaDto trancaCadastrada = trancaService.cadastrar(novaTranca);
            return ResponseEntity.status(HttpStatus.OK).body(trancaCadastrada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ErroDto("DADOS_INVALIDOS","Nao foi possivel cadastrar a tranca no sistema"));
        }

    }

    @GetMapping
    public ResponseEntity<?> listarTrancasDoSistema(){
        List<TrancaDto> trancas = trancaService.listarTrancas();
        return ResponseEntity.ok(trancas);
    }

    @GetMapping("/{idTranca}")
    public ResponseEntity<?> buscarTrancaPorId(@PathVariable int idTranca){
        TrancaDto resultado = trancaService.buscaPorId(idTranca);

        if (resultado != null){
            return ResponseEntity.ok(resultado);
        }

        ErroDto erro = new ErroDto("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " nao encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @PutMapping("/{idTranca}")
    public ResponseEntity<?> editarTranca(@PathVariable int idTranca, @RequestBody @Valid NovaTrancaDto novaTrancaDto){
        TrancaDto trancaEditada = trancaService.editarTranca(idTranca, novaTrancaDto);
        if (trancaEditada != null){
            return ResponseEntity.ok(trancaEditada);
        }

        ErroDto erro = new ErroDto("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " não encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @DeleteMapping("/{idTranca}")
    public ResponseEntity<?> deletarTranca(@PathVariable int idTranca){
        boolean removida = trancaService.deletaTranca(idTranca);
        if (removida){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErroDto("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " não encontrada."));
    }
}
