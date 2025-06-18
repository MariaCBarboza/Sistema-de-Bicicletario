package com.mariaClara.SistemaBicicletario.controllers;

import com.mariaClara.SistemaBicicletario.dto.ErroDto;
import com.mariaClara.SistemaBicicletario.dto.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.dto.TotenDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.services.TotenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toten")
public class TotenController {
    private final TotenService totenService;

    public TotenController(TotenService totenService) {
        this.totenService = totenService;
    }
    @PostMapping
    public ResponseEntity<TotenDto> cadastraToten(@RequestBody @Valid NovoTotenDto novoToten){
         TotenDto totenCadastrado = totenService.cadastrarToten(novoToten);
         return ResponseEntity.status(HttpStatus.OK).body(totenCadastrado);
    }

    @GetMapping("/")
    public ResponseEntity<List<TotenDto>> listarTotens(){
        List<TotenDto> totens = totenService.listarTotens();
        return ResponseEntity.ok(totens);
    }

    @PutMapping("/{idToten}")
    public ResponseEntity<TotenDto>editaToten(@PathVariable int idToten, @RequestBody @Valid NovoTotenDto novoToten){
        TotenDto totenEditado = totenService.atualizarToten(idToten, novoToten);
        if (totenEditado == null){
            throw new RecursoNaoEncontradoException("Toten com Id " +  idToten + " nao encontrado");
        }

        return ResponseEntity.ok(totenEditado);
    }

    @DeleteMapping("/{idToten}")
    public ResponseEntity<Void> removerToten(@PathVariable int idToten){
        totenService.deletarToten(idToten);
        return ResponseEntity.ok().build();
    }
}
