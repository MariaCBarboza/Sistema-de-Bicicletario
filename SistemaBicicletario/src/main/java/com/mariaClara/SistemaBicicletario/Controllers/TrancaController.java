package com.mariaClara.SistemaBicicletario.Controllers;

import com.mariaClara.SistemaBicicletario.DTO.Erro;
import com.mariaClara.SistemaBicicletario.DTO.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.DTO.Tranca;
import com.mariaClara.SistemaBicicletario.Services.TrancaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trancas")
public class TrancaController {
    private final TrancaService service;

    public TrancaController(TrancaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTranca(@RequestBody @Valid NovaTrancaDto novaTranca){
        try{
            Tranca trancaCadastrada = service.cadastrar(novaTranca);
            return ResponseEntity.status(HttpStatus.OK).body(trancaCadastrada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erro("DADOS_INVALIDOS","Nao foi possivel cadastrar a tranca no sistema"));
        }

    }

    @GetMapping
    public ResponseEntity<?> listarTrancasDoSistema(){
        List<Tranca> trancas = service.listarTrancas();
        return ResponseEntity.ok(trancas);
    }

    @GetMapping("/{idTranca}")
    public ResponseEntity<?> buscarPorId(@PathVariable int idTranca){
        Tranca resultado = service.buscaPorId(idTranca);

        if (resultado != null){
            return ResponseEntity.ok(resultado);
        }

        Erro erro = new Erro("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " nao encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @PutMapping("/{idTranca}")
    public ResponseEntity<?> editarTranca(@PathVariable int idTranca, @RequestBody @Valid NovaTrancaDto novaTrancaDto){
        Tranca trancaEditada = service.editarTranca(idTranca, novaTrancaDto);
        if (trancaEditada != null){
            return ResponseEntity.ok(trancaEditada);
        }

        Erro erro = new Erro("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " não encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @DeleteMapping("/{idTranca}")
    public ResponseEntity<?> deletarTranca(@PathVariable int idTranca){
        boolean removida = service.deletaTranca(idTranca);
        if (removida){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new Erro("TRANCA_NAO_ENCONTRADA", "Tranca com ID " + idTranca + " não encontrada."));
    }
}
