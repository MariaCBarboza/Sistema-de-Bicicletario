package com.mariaClara.SistemaBicicletario.Services;

import com.mariaClara.SistemaBicicletario.DTO.Bicicleta;
import com.mariaClara.SistemaBicicletario.DTO.NovaBicicleta;
import com.mariaClara.SistemaBicicletario.Mapper.BicicletaMapper;
import com.mariaClara.SistemaBicicletario.Model.BicicletaEntity;
import com.mariaClara.SistemaBicicletario.Repository.BicicletaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BicicletaService {
    private final BicicletaRepository repository;

    public BicicletaService(BicicletaRepository repository){
        this.repository = repository;
    }

    public Bicicleta cadastrar(NovaBicicleta novaBicicleta){
        BicicletaEntity entity = BicicletaMapper.toEntity(novaBicicleta);
        BicicletaEntity salvo = repository.save(entity);

        return BicicletaMapper.toDto(salvo);

    }

    public List<Bicicleta> listarBicicletas(){
        List<Bicicleta> lista = repository.findAll()
                .stream()
                .map(entity -> BicicletaMapper.toDto(entity))
                .collect(Collectors.toList());
        return lista;
    }


    public Bicicleta buscaPorId(Long idBicicleta){
        BicicletaEntity bicicleta = repository.findById(idBicicleta).orElse(null);
        if(bicicleta == null){
            return null;
        }

        return BicicletaMapper.toDto(bicicleta);
    }

    public Bicicleta atualizarBicicleta(Long idBicicleta, NovaBicicleta novaBicicleta){
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

    public boolean removeBicicleta(Long id){
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
