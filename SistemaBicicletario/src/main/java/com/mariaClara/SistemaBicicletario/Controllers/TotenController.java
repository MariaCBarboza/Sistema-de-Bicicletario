package com.mariaClara.SistemaBicicletario.Controllers;

import com.mariaClara.SistemaBicicletario.DTO.Erro;
import com.mariaClara.SistemaBicicletario.DTO.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.DTO.Toten;
import com.mariaClara.SistemaBicicletario.Services.TotenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/totens")
public class TotenController {
    private final TotenService service;

    public TotenController(TotenService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> cadastraToten(@RequestBody @Valid NovoTotenDto novoToten){
        try{
            Toten totenCadastrado = service.cadastrar(novoToten);
            return ResponseEntity.status(HttpStatus.OK).body(totenCadastrado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erro("DADOS_INVALIDOS","Nao foi possivel cadastrar o toten"));
        }

    }

    @GetMapping
    public ResponseEntity<List<Toten>> listarTotens(){
        List<Toten> totens = service.listarTotens();
        return ResponseEntity.ok(totens);
    }

    @PutMapping("/{idToten}")
    public ResponseEntity<?>editaToten(@PathVariable int idToten, @RequestBody @Valid NovoTotenDto novoToten){
        Toten totenEditado = service.atualizarBicicleta(idToten, novoToten);
        if (totenEditado!=null){
            return ResponseEntity.ok(totenEditado);
        }

        Erro erro = new Erro("TOTEN_NAO_ENCONTRADO", "Toten com ID " + idToten + " não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @DeleteMapping("/{idToten}")
    public ResponseEntity<?> remover(@PathVariable int idToten){
        boolean removido = service.deletarToten(idToten);
        if (removido){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new Erro("BICICLETA_NAO_ENCONTRADA", "Bicicleta com ID " + idToten + " não encontrada."));
    }
}
