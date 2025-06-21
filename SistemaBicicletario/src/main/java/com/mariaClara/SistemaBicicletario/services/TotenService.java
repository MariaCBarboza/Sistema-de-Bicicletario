package com.mariaClara.SistemaBicicletario.services;


import com.mariaClara.SistemaBicicletario.dto.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.dto.TotenDto;
import com.mariaClara.SistemaBicicletario.dto.TrancaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.mapper.TotenMapper;
import com.mariaClara.SistemaBicicletario.mapper.TrancaMapper;
import com.mariaClara.SistemaBicicletario.model.TotenEntity;
import com.mariaClara.SistemaBicicletario.model.TrancaEntity;
import com.mariaClara.SistemaBicicletario.repository.TotenRepository;
import com.mariaClara.SistemaBicicletario.repository.TrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TotenService {
    @Autowired
    private TrancaRepository trancaRepository;

    private final TotenRepository repository;

    public TotenService(TotenRepository repository) {
        this.repository = repository;
    }

    public TotenDto cadastrarToten(NovoTotenDto novoToten){
        TotenEntity toten = TotenMapper.toEntity(novoToten);
        TotenEntity salvo = repository.save(toten);

        return TotenMapper.toDto(salvo);
    }

    public List<TotenDto> listarTotens(){
        return repository.findAll()
                .stream()
                .map(entity -> TotenMapper.toDto(entity))
                .collect(Collectors.toList());
    }

    public TotenDto atualizarToten(int idToten, NovoTotenDto novoToten){
        TotenEntity toten = repository.findById(idToten).orElse(null);
        if (toten == null){
            return null;
        }

        toten.setLocalizacao(novoToten.getLocalizacao());
        toten.setDescricao(novoToten.getDescricao());

        TotenEntity totenEntity = repository.save(toten);

        return TotenMapper.toDto(totenEntity);
    }

    public void deletarToten(int id){
        if (!repository.existsById(id)){
            throw new RecursoNaoEncontradoException("Toten com Id " + id + " nao encontrado");
        }
        repository.deleteById(id);
    }

    public List<TrancaDto> listarTrancasDoToten(int idToten){
        TotenEntity toten = repository.findById(idToten)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Toten não encontardo"));

        List<TrancaEntity> trancas = trancaRepository.findByTotenEntity(toten);

        return trancas.stream()
                .map(trancaEntity -> TrancaMapper.toDto(trancaEntity))
                .collect(Collectors.toList());

    }


}
