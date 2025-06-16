package com.mariaClara.SistemaBicicletario.services;


import com.mariaClara.SistemaBicicletario.dto.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.dto.TotenDto;
import com.mariaClara.SistemaBicicletario.mapper.TotenMapper;
import com.mariaClara.SistemaBicicletario.model.TotenEntity;
import com.mariaClara.SistemaBicicletario.repository.TotenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TotenService {
    private final TotenRepository repository;

    public TotenService(TotenRepository repository) {
        this.repository = repository;
    }

    public TotenDto cadastrar(NovoTotenDto novoToten){
        TotenEntity toten = TotenMapper.toEntity(novoToten);
        TotenEntity salvo = repository.save(toten);

        return TotenMapper.toDto(salvo);
    }

    public List<TotenDto> listarTotens(){
        List<TotenDto> lista = repository.findAll()
                .stream()
                .map(entity -> TotenMapper.toDto(entity))
                .collect(Collectors.toList());
        return lista;
    }

    public TotenDto atualizarBicicleta(int idToten, NovoTotenDto novoToten){
        TotenEntity toten = repository.findById(idToten).orElse(null);
        if (toten == null){
            return null;
        }

        toten.setLocalizacao(novoToten.getLocalizacao());
        toten.setDescricao(novoToten.getDescricao());

        TotenEntity totenEntity = repository.save(toten);

        return TotenMapper.toDto(totenEntity);
    }

    public boolean deletarToten(int id){
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }


}
