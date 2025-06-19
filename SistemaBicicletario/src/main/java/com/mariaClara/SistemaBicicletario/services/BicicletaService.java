package com.mariaClara.SistemaBicicletario.services;

import com.mariaClara.SistemaBicicletario.dto.BicicletaDto;
import com.mariaClara.SistemaBicicletario.dto.IntegrarBicicletaNaRedeDto;
import com.mariaClara.SistemaBicicletario.dto.NovaBicicletaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.mapper.BicicletaMapper;
import com.mariaClara.SistemaBicicletario.model.BicicletaEntity;
import com.mariaClara.SistemaBicicletario.model.StatusBicicleta;
import com.mariaClara.SistemaBicicletario.model.StatusTranca;
import com.mariaClara.SistemaBicicletario.model.TrancaEntity;
import com.mariaClara.SistemaBicicletario.repository.BicicletaRepository;
import com.mariaClara.SistemaBicicletario.repository.TrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BicicletaService {

    @Autowired
    private TrancaRepository trancaRepository;
    private final BicicletaRepository repository;

    public BicicletaService(BicicletaRepository repository){
        this.repository = repository;
    }

    public BicicletaDto cadastrar(NovaBicicletaDto novaBicicleta){
        BicicletaEntity entity = BicicletaMapper.toEntity(novaBicicleta);
        BicicletaEntity salvo = repository.save(entity);

        return BicicletaMapper.toDto(salvo);

    }

    public List<BicicletaDto> listarBicicletas(){
        return repository.findAll()
                .stream()
                .map(entity -> BicicletaMapper.toDto(entity))
                .collect(Collectors.toList());
    }


    public BicicletaDto buscaPorId(int idBicicleta){
        BicicletaEntity bicicleta = repository.findById(idBicicleta).orElse(null);
        if(bicicleta == null){
            return null;
        }

        return BicicletaMapper.toDto(bicicleta);
    }

    public BicicletaDto atualizarBicicleta(int idBicicleta, NovaBicicletaDto novaBicicleta){
        BicicletaEntity bicicleta = repository.findById(idBicicleta).orElse(null);
        if (bicicleta == null){
            return null;
        }

        bicicleta.setMarca(novaBicicleta.getMarca());
        bicicleta.setModelo(novaBicicleta.getModelo());
        bicicleta.setAno(novaBicicleta.getAno());
        bicicleta.setNumero(novaBicicleta.getNumero());
        bicicleta.setStatus(novaBicicleta.getStatus());

        BicicletaEntity bicicletaEditada = repository.save(bicicleta);

        return BicicletaMapper.toDto(bicicletaEditada);

    }

    public void removeBicicleta(int id){
        if (!repository.existsById(id)){
           throw new RecursoNaoEncontradoException("Bicicleta com Id " + id + " nao encontrada");
        }
        repository.deleteById(id);
    }

    public BicicletaDto atualizaEstadoBicicleta(int idBicicleta, String acao){
        BicicletaEntity bicicletaEntity = repository.findById(idBicicleta).orElseThrow(
                () -> new RecursoNaoEncontradoException("Bicicleta com Id " + " nao encontrada"));

        StatusBicicleta novoStatus;
        try {
            novoStatus = StatusBicicleta.valueOf(acao.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Status invalido: " + acao);
        }

        bicicletaEntity.setStatus(novoStatus);
        repository.save(bicicletaEntity);
        return BicicletaMapper.toDto(bicicletaEntity);
    }

    public BicicletaDto integrarBicicletaNaRede(IntegrarBicicletaNaRedeDto dto){
        BicicletaEntity bicicleta = repository.findById(dto.getIdBicicleta()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Bicicleta nao encontrada"));

        TrancaEntity tranca = trancaRepository.findById(dto.getIdTranca()).orElseThrow(
                () -> new RecursoNaoEncontradoException("Tranca nao encontrada"));

        if (!(bicicleta.getStatus() == StatusBicicleta.NOVA || bicicleta.getStatus() == StatusBicicleta.REPARO_SOLICITADO)){// aqui nao sei se em reparo ou reparo solicitado
            throw new IllegalArgumentException("Status Bicicleta não permite integração na rede ");
        }
        if (tranca.getStatusTranca() != StatusTranca.LIVRE){
            throw new IllegalArgumentException("Tranca ocupada");
        }

        bicicleta.setStatus(StatusBicicleta.DISPONIVEL);
        repository.save(bicicleta);

        tranca.setStatusTranca(StatusTranca.OCUPADA);
        tranca.setBicicleta(bicicleta.getId());
        trancaRepository.save(tranca);

        return BicicletaMapper.toDto(bicicleta);
    }
}
