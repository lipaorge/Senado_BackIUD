package com.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iudigital.model.Partido;

@Repository
public interface IPartidoRepository extends JpaRepository<Partido, Long>{
	

    /**
     * Método que busca un partido por su nombre
     * @param nombre
     * @return
     */
    Partido findByNombre(String nombre);

}
