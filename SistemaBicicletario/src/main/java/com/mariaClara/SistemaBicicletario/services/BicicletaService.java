package com.mariaClara.SistemaBicicletario.services;

import com.mariaClara.SistemaBicicletario.dto.BicicletaDto;
import com.mariaClara.SistemaBicicletario.dto.NovaBicicletaDto;
import com.mariaClara.SistemaBicicletario.exception.RecursoNaoEncontradoException;
import com.mariaClara.SistemaBicicletario.mapper.BicicletaMapper;
import com.mariaClara.SistemaBicicletario.model.BicicletaEntity;
import com.mariaClara.SistemaBicicletario.repository.BicicletaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BicicletaService {
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


    public BicicletaDto buscaPorId(Long idBicicleta){
        BicicletaEntity bicicleta = repository.findById(idBicicleta).orElse(null);
        if(bicicleta == null){
            return null;
        }

        return BicicletaMapper.toDto(bicicleta);
    }

    public BicicletaDto atualizarBicicleta(Long idBicicleta, NovaBicicletaDto novaBicicleta){
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

    public void removeBicicleta(Long id){
        if (!repository.existsById(id)){
           throw new RecursoNaoEncontradoException("Bicicleta com Id " + id + " nao encontrada");
        }
        repository.deleteById(id);
    }
}
