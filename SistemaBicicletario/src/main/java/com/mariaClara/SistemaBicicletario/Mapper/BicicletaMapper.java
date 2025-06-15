package com.mariaClara.SistemaBicicletario.Mapper;

import com.mariaClara.SistemaBicicletario.DTO.Bicicleta;
import com.mariaClara.SistemaBicicletario.DTO.NovaBicicleta;
import com.mariaClara.SistemaBicicletario.Model.BicicletaEntity;

public class BicicletaMapper {
    public static BicicletaEntity toEntity(NovaBicicleta dto){
        BicicletaEntity bicicletaEntity = new BicicletaEntity();
        bicicletaEntity.setMarca(dto.getMarca());
        bicicletaEntity.setModelo(dto.getModelo());
        bicicletaEntity.setAno(dto.getAno());
        bicicletaEntity.setNumero(dto.getNumero());
        bicicletaEntity.setStatus(dto.getStatus());

        return bicicletaEntity;
    }

    public static Bicicleta toDto(BicicletaEntity entity){
        Bicicleta bicicletaDto = new Bicicleta();
        bicicletaDto.setId(entity.getId());
        bicicletaDto.setMarca(entity.getMarca());
        bicicletaDto.setModelo(entity.getModelo());
        bicicletaDto.setAno(entity.getAno());
        bicicletaDto.setNumero(entity.getNumero());
        bicicletaDto.setStatus(entity.getStatus());

        return bicicletaDto;
    }
}
