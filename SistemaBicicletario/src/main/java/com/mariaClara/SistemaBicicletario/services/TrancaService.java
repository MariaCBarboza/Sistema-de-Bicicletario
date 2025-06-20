package com.mariaClara.SistemaBicicletario.services;

import com.mariaClara.SistemaBicicletario.dto.IntegraTrancaNaRedeDto;
import com.mariaClara.SistemaBicicletario.dto.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.dto.RetirarTrancaDaRedeDto;
import com.mariaClara.SistemaBicicletario.dto.TrancaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.mapper.TrancaMapper;
import com.mariaClara.SistemaBicicletario.model.AcaoTranca;
import com.mariaClara.SistemaBicicletario.model.StatusTranca;
import com.mariaClara.SistemaBicicletario.model.TotenEntity;
import com.mariaClara.SistemaBicicletario.model.TrancaEntity;
import com.mariaClara.SistemaBicicletario.repository.TotenRepository;
import com.mariaClara.SistemaBicicletario.repository.TrancaRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrancaService {
    @Autowired
    private TotenRepository totenRepository;
    private final TrancaRepository repository;

    public TrancaService(TrancaRepository repository) {
        this.repository = repository;
    }

    public TrancaDto cadastrarTranca(NovaTrancaDto novaTranca){
        TrancaEntity entity = TrancaMapper.toEntity(novaTranca);
        TrancaEntity salvo = repository.save(entity);

        return TrancaMapper.toDto(salvo);
    }

    public List<TrancaDto> listarTrancas(){
        return repository.findAll()
                .stream()
                .map(trancaEntity -> TrancaMapper.toDto(trancaEntity))
                .collect(Collectors.toList());
    }

    public TrancaDto buscaTrancaPorId(int idTranca){
        TrancaEntity tranca = repository.findById(idTranca).orElse(null);
        if(tranca == null){
            return null;
        }

        return TrancaMapper.toDto(tranca);
    }

    public TrancaDto editarTranca(int idTranca, NovaTrancaDto novaTranca){
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

    public void deletaTranca(int idTranca){
        if (!repository.existsById(idTranca)){
            throw new RecursoNaoEncontradoException("Tranca com Id " + idTranca + " nao encontrada");
        }
        repository.deleteById(idTranca);
    }
    public TrancaDto integraTrancaNaRede(IntegraTrancaNaRedeDto dto){
        TrancaEntity tranca = repository.findById(dto.getIdTranca())
                .orElseThrow(()-> new RecursoNaoEncontradoException("Tranca não encontrada"));

        TotenEntity toten = totenRepository.findById(dto.getIdToten())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Toten não encontrado"));

        if (!(tranca.getStatusTranca() == StatusTranca.NOVA || tranca.getStatusTranca()== StatusTranca.EM_REPARO)){
            throw new IllegalArgumentException("Status da Traca Nao permite integrar na rede");
        }

        tranca.setStatusTranca(StatusTranca.LIVRE);
        tranca.setTotenEntity(toten);
        repository.save(tranca);

        return TrancaMapper.toDto(tranca);

    }

    public TrancaDto retirarTrancaDaRede(RetirarTrancaDaRedeDto dto){
        TrancaEntity tranca = repository.findById(dto.getIdTranca())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tranca não encontrada"));

        TotenEntity toten = totenRepository.findById(dto.getIdToten())
                .orElseThrow(()-> new RecursoNaoEncontradoException("Toten não encontrado"));

        if (tranca.getTotenEntity().getId() != toten.getId()){
            throw new IllegalArgumentException("Tranca não pertence ao toten indicado");
        }

        if (tranca.getStatusTranca() == StatusTranca.APOSENTADA){
            throw new IllegalArgumentException("Tranca já aposentada");
        }

        StatusTranca novoStatus = dto.getStatusTranca();
        if (novoStatus == StatusTranca.EM_REPARO){
            if (tranca.getStatusTranca() != StatusTranca.REPARO_SOLICITADO){ // no swagger não tem esse status mas ele é definido na especificção dos casos de uso
                throw new IllegalArgumentException("Apenas Trancas com Status Reparo_Solicitado podem ser retiradas para Reparo");
            }
            tranca.setStatusTranca(novoStatus);
        } else if (novoStatus == StatusTranca.APOSENTADA){
            tranca.setStatusTranca(novoStatus);
        }else {
            throw new IllegalArgumentException("Status não permite retirada ");
        }

        repository.save(tranca);
        return TrancaMapper.toDto(tranca);
    }

    public TrancaDto atualizaStatusTranca(int idTranca, String acao){
        TrancaEntity tranca = repository.findById(idTranca)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tranca não encontrada"));
        AcaoTranca acaoTranca;

        try {
            acaoTranca = AcaoTranca.valueOf(acao.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Acao invalida: " + acao);
        }

        if (acaoTranca == AcaoTranca.TRANCAR){
            tranca.setStatusTranca(StatusTranca.OCUPADA);
        } else if (acaoTranca == AcaoTranca.DESTRANCAR) {
            tranca.setStatusTranca(StatusTranca.LIVRE);
        }

        repository.save(tranca);
        return TrancaMapper.toDto(tranca);
    }
}
