package com.mariaClara.SistemaBicicletario.mapper;

import com.mariaClara.SistemaBicicletario.dto.NovoTotenDto;
import com.mariaClara.SistemaBicicletario.dto.TotenDto;
import com.mariaClara.SistemaBicicletario.model.TotenEntity;

public class TotenMapper {

    public static TotenEntity toEntity(NovoTotenDto dto){
        TotenEntity entity = new TotenEntity();

        entity.setLocalizacao(dto.getLocalizacao());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }

    public static TotenDto toDto(TotenEntity entity){
        TotenDto toten = new TotenDto();

        toten.setId(entity.getId());
        toten.setLocalizacao(entity.getLocalizacao());
        toten.setDescricao(entity.getDescricao());
        return toten;
    }

}
