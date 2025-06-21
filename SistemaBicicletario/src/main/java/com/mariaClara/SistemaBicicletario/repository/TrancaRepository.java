package com.mariaClara.SistemaBicicletario.repository;

import com.mariaClara.SistemaBicicletario.dto.TrancaDto;
import com.mariaClara.SistemaBicicletario.model.TotenEntity;
import com.mariaClara.SistemaBicicletario.model.TrancaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrancaRepository extends JpaRepository<TrancaEntity, Integer> {
    List<TrancaEntity> findByTotenEntity(TotenEntity totenEntity);

}
