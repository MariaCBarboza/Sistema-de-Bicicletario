package com.mariaClara.SistemaBicicletario.Services;

import com.mariaClara.SistemaBicicletario.DTO.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.DTO.Tranca;
import com.mariaClara.SistemaBicicletario.Mapper.TrancaMapper;
import com.mariaClara.SistemaBicicletario.Model.TrancaEntity;
import com.mariaClara.SistemaBicicletario.Repository.TrancaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrancaService {
    private final TrancaRepository repository;

    public TrancaService(TrancaRepository repository) {
        this.repository = repository;
    }

    public Tranca cadastrar(NovaTrancaDto novaTranca){
        TrancaEntity entity = TrancaMapper.toEntity(novaTranca);
        TrancaEntity salvo = repository.save(entity);

        return TrancaMapper.toDto(salvo);
    }

    public List<Tranca> listarTrancas(){
        List<Tranca> trancas = repository.findAll()
                .stream()
                .map(trancaEntity -> TrancaMapper.toDto(trancaEntity))
                .collect(Collectors.toList());
        return trancas;
    }

    public Tranca buscaPorId(int idTranca){
        TrancaEntity tranca = repository.findById(idTranca).orElse(null);
        if(tranca == null){
            return null;
        }

        return TrancaMapper.toDto(tranca);
    }

    public Tranca editarTranca(int idTranca, NovaTrancaDto novaTranca){
        TrancaEntity tranca = repository.findById(idTranca).orElse(null);
        if (tranca == null){
            return null;
        }

        tranca.setNumero(novaTranca.getNumero());
        tranca.setLocalizacao(novaTranca.getLocalizacao());
        tranca.setModelo(novaTranca.getModelo());
        tranca.setAnoDeFabricacao(novaTranca.getAnoDeFabricacao());
        tranca.setStatusTranca(novaTranca.getStatusTranca());

        TrancaEntity trancaEditada = repository.save(tranca);

        return TrancaMapper.toDto(trancaEditada);

    }

    public boolean deletaTranca(int idTranca){
        if (!repository.existsById(idTranca)){
            return false;
        }
        repository.deleteById(idTranca);
        return true;
    }
}
