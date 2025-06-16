package com.mariaClara.SistemaBicicletario.repository;

import com.mariaClara.SistemaBicicletario.model.TrancaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrancaRepository extends JpaRepository<TrancaEntity, Integer> {

}
