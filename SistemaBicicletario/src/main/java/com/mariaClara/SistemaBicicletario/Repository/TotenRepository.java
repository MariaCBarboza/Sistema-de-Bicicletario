package com.mariaClara.SistemaBicicletario.Repository;


import com.mariaClara.SistemaBicicletario.Model.TotenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotenRepository extends JpaRepository <TotenEntity, Integer> {
}
