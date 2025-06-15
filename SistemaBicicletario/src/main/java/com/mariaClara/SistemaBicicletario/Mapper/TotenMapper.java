package com.mariaClara.SistemaBicicletario.Mapper;

import com.mariaClara.SistemaBicicletario.DTO.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.DTO.Toten;
import com.mariaClara.SistemaBicicletario.Model.TotenEntity;

public class TotenMapper {

    public static TotenEntity toEntity(NovoTotenDto dto){
        TotenEntity entity = new TotenEntity();

        entity.setLocalizacao(dto.getLocalizacao());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }

    public static Toten toDto(TotenEntity entity){
        Toten toten = new Toten();

        toten.setId(entity.getId());
        toten.setLocalizacao(entity.getLocalizacao());
        toten.setDescricao(entity.getDescricao());
        return toten;
    }

}
