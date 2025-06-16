package com.mariaClara.SistemaBicicletario.repository;

import com.mariaClara.SistemaBicicletario.model.BicicletaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicicletaRepository extends JpaRepository<BicicletaEntity, Long>{
}
