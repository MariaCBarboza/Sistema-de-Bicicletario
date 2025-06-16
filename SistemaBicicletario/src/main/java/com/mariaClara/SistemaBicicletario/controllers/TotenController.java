package com.mariaClara.SistemaBicicletario.controllers;

import com.mariaClara.SistemaBicicletario.dto.ErroDto;
import com.mariaClara.SistemaBicicletario.dto.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.dto.TotenDto;
import com.mariaClara.SistemaBicicletario.services.TotenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/totens")
public class TotenController {
    private final TotenService totenService;

    public TotenController(TotenService totenService) {
        this.totenService = totenService;
    }
    @PostMapping
    public ResponseEntity<?> cadastraToten(@RequestBody @Valid NovoTotenDto novoToten){
        try{
            TotenDto totenCadastrado = totenService.cadastrar(novoToten);
            return ResponseEntity.status(HttpStatus.OK).body(totenCadastrado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ErroDto("DADOS_INVALIDOS","Nao foi possivel cadastrar o toten"));
        }

    }

    @GetMapping
    public ResponseEntity<List<TotenDto>> listarTotens(){
        List<TotenDto> totens = totenService.listarTotens();
        return ResponseEntity.ok(totens);
    }

    @PutMapping("/{idToten}")
    public ResponseEntity<?>editaToten(@PathVariable int idToten, @RequestBody @Valid NovoTotenDto novoToten){
        TotenDto totenEditado = totenService.atualizarBicicleta(idToten, novoToten);
        if (totenEditado!=null){
            return ResponseEntity.ok(totenEditado);
        }

        ErroDto erro = new ErroDto("TOTEN_NAO_ENCONTRADO", "Toten com ID " + idToten + " não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @DeleteMapping("/{idToten}")
    public ResponseEntity<?> removerToten(@PathVariable int idToten){
        boolean removido = totenService.deletarToten(idToten);
        if (removido){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErroDto("BICICLETA_NAO_ENCONTRADA", "Bicicleta com ID " + idToten + " não encontrada."));
    }
}
