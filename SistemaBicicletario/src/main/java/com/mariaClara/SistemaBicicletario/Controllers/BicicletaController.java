package com.mariaClara.SistemaBicicletario.Controllers;

import com.mariaClara.SistemaBicicletario.DTO.Bicicleta;
import com.mariaClara.SistemaBicicletario.DTO.Erro;
import com.mariaClara.SistemaBicicletario.DTO.NovaBicicleta;
import com.mariaClara.SistemaBicicletario.DTO.Toten;
import com.mariaClara.SistemaBicicletario.Services.BicicletaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/bicicletas")
public class BicicletaController {
    private final BicicletaService service;

    public BicicletaController(BicicletaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaBicicleta novaBicicleta){
        try{
            Bicicleta bicicletaCadastrada = service.cadastrar(novaBicicleta);
            return ResponseEntity.status(HttpStatus.OK).body(bicicletaCadastrada);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erro("DADOS_INVALIDOS","Nao foi possivel cadastrar a bicicleta"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Bicicleta>>listarBicicletas(){
        List<Bicicleta> bicicletas = service.listarBicicletas();
        return ResponseEntity.ok(bicicletas);
    }


    @GetMapping("/{idBicicleta}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idBicicleta){
        Bicicleta resultado = service.buscaPorId(idBicicleta);

        if (resultado != null){
            return ResponseEntity.ok(resultado);
        }

        Erro erro = new Erro("BICICLETA_NAO_ENCONTRADA", "Bicicleta com ID " + idBicicleta + " nao encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


    @PutMapping("/{idBicicleta}")
    public ResponseEntity<?> editarBicicleta(@PathVariable Long idBicicleta, @RequestBody @Valid NovaBicicleta novaBicicleta){
        Bicicleta bicicletaEditada = service.atualizarBicicleta(idBicicleta, novaBicicleta);
        if (bicicletaEditada != null){
            return ResponseEntity.ok(bicicletaEditada);
        }

        Erro erro = new Erro("BICICLETA_NAO_ENCONTRADA", "Bicicleta com ID " + idBicicleta + " não encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @DeleteMapping("/{idBicicleta}")
    public ResponseEntity<?> remover(@PathVariable Long idBicicleta){
        boolean removida = service.removeBicicleta(idBicicleta);
        if (removida){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new Erro("BICICLETA_NAO_ENCONTRADA", "Bicicleta com ID " + idBicicleta + " não encontrada."));
    }




}
