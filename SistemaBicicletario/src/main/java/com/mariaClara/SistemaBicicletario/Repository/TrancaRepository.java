package com.mariaClara.SistemaBicicletario.Repository;

import com.mariaClara.SistemaBicicletario.Model.TrancaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrancaRepository extends JpaRepository<TrancaEntity, Integer> {

}
