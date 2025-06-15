package com.mariaClara.SistemaBicicletario.Mapper;

import com.mariaClara.SistemaBicicletario.DTO.NovaTrancaDto;
import com.mariaClara.SistemaBicicletario.DTO.Tranca;
import com.mariaClara.SistemaBicicletario.Model.TrancaEntity;

public class TrancaMapper {
    public static TrancaEntity toEntity(NovaTrancaDto dto){
        TrancaEntity tranca = new TrancaEntity();

        tranca.setNumero(dto.getNumero());
        tranca.setModelo(dto.getModelo());
        tranca.setAnoDeFabricacao(dto.getAnoDeFabricacao());
        tranca.setLocalizacao(dto.getLocalizacao());
        tranca.setStatusTranca(dto.getStatusTranca());
        // duvida aqui nao sei como setar bicicleta entao nao associei a nada

        return tranca;
    }

    public static Tranca toDto(TrancaEntity trancaEntity){
        Tranca trancaDto = new Tranca();
        trancaDto.setId(trancaEntity.getId());
        trancaDto.setNumero(trancaDto.getNumero());
        trancaDto.setLocalizacao(trancaEntity.getLocalizacao());
        trancaDto.setModelo(trancaEntity.getModelo());
        trancaDto.setAnoDeFabricacao(trancaEntity.getAnoDeFabricacao());
        trancaDto.setBicicleta(trancaEntity.getBicicleta());
        trancaDto.setStatusTranca(trancaEntity.getStatusTranca());

        return trancaDto;
    }
}
