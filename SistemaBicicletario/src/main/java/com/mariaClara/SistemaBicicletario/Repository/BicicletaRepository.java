package com.mariaClara.SistemaBicicletario.Repository;

import com.mariaClara.SistemaBicicletario.Model.BicicletaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicicletaRepository extends JpaRepository<BicicletaEntity, Long>{
}
