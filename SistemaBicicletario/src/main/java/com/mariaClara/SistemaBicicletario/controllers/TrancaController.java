package com.mariaClara.SistemaBicicletario.controllers;

import com.mariaClara.SistemaBicicletario.dto.IntegraTrancaNaRedeDto;
import com.mariaClara.SistemaBicicletario.dto.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.dto.RetirarTrancaDaRedeDto;
import com.mariaClara.SistemaBicicletario.dto.TrancaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.services.TrancaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tranca")
public class TrancaController {
    private final TrancaService trancaService;

    public TrancaController(TrancaService trancaService) {
        this.trancaService = trancaService;
    }

    @PostMapping
    public ResponseEntity<TrancaDto> cadastrarTranca(@RequestBody @Valid NovaTrancaDto novaTranca){
        TrancaDto trancaCadastrada = trancaService.cadastrarTranca(novaTranca);
        return ResponseEntity.status(HttpStatus.OK).body(trancaCadastrada);

    }

    @GetMapping("/")
    public ResponseEntity<List<TrancaDto>> listarTrancasDoSistema(){
        List<TrancaDto> trancas = trancaService.listarTrancas();
        return ResponseEntity.ok(trancas);
    }

    @GetMapping("/{idTranca}")
    public ResponseEntity<TrancaDto> buscarTrancaPorId(@PathVariable int idTranca){
        TrancaDto resultado = trancaService.buscaTrancaPorId(idTranca);

        if (resultado == null){
            throw new RecursoNaoEncontradoException("Tranca com Id " +  idTranca + " nao encontrada");
        }
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{idTranca}")
    public ResponseEntity<TrancaDto> editarTranca(@PathVariable int idTranca, @RequestBody @Valid NovaTrancaDto novaTrancaDto){
        TrancaDto trancaEditada = trancaService.editarTranca(idTranca, novaTrancaDto);
        if (trancaEditada == null){
          throw new RecursoNaoEncontradoException("Tranca com ID " + idTranca + " não encontrada.");
        }

        return ResponseEntity.ok(trancaEditada);
    }

    @DeleteMapping("/{idTranca}")
    public ResponseEntity<Void> deletarTranca(@PathVariable int idTranca){
       trancaService.deletaTranca(idTranca);
       return ResponseEntity.ok().build();
    }
    @PostMapping("/integrarNaRede")
    public ResponseEntity<TrancaDto> integrarNaRedeDeTotens(@RequestBody @Valid IntegraTrancaNaRedeDto dto){
        TrancaDto tranca = trancaService.integraTrancaNaRede(dto);
        return ResponseEntity.ok(tranca);
    }

    @PostMapping("/retirarDaRede")
    public ResponseEntity<TrancaDto> retirarDaRede(@RequestBody @Valid RetirarTrancaDaRedeDto dto){
        TrancaDto tranca = trancaService.retirarTrancaDaRede(dto);
        return ResponseEntity.ok(tranca);
    }

    @PostMapping("/{idTranca}/status/{acao}")
    public ResponseEntity<TrancaDto> atualizaStatusTranca(@PathVariable int idTranca, @PathVariable String acao){
        TrancaDto tranca = trancaService.atualizaStatusTranca(idTranca, acao);
        return ResponseEntity.ok(tranca);
    }
}
