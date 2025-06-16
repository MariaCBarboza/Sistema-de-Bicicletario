package com.mariaClara.SistemaBicicletario.repository;


import com.mariaClara.SistemaBicicletario.model.TotenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotenRepository extends JpaRepository <TotenEntity, Integer> {
}
