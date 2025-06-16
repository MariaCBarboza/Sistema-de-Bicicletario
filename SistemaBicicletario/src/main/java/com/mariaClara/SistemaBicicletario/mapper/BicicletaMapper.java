package com.mariaClara.SistemaBicicletario.mapper;

import com.mariaClara.SistemaBicicletario.dto.BicicletaDto;
import com.mariaClara.SistemaBicicletario.dto.NovaBicicletaDto;
import com.mariaClara.SistemaBicicletario.model.BicicletaEntity;

public class BicicletaMapper {
    public static BicicletaEntity toEntity(NovaBicicletaDto dto){
        BicicletaEntity bicicletaEntity = new BicicletaEntity();
        bicicletaEntity.setMarca(dto.getMarca());
        bicicletaEntity.setModelo(dto.getModelo());
        bicicletaEntity.setAno(dto.getAno());
        bicicletaEntity.setNumero(dto.getNumero());
        bicicletaEntity.setStatus(dto.getStatus());

        return bicicletaEntity;
    }

    public static BicicletaDto toDto(BicicletaEntity entity){
        BicicletaDto bicicletaDto = new BicicletaDto();
        bicicletaDto.setId(entity.getId());
        bicicletaDto.setMarca(entity.getMarca());
        bicicletaDto.setModelo(entity.getModelo());
        bicicletaDto.setAno(entity.getAno());
        bicicletaDto.setNumero(entity.getNumero());
        bicicletaDto.setStatus(entity.getStatus());

        return bicicletaDto;
    }
}
