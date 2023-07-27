package com.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iudigital.model.Proyecto;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto, Long>{
	

    /**
     * Método que busca un proyecto por su título
     * @param titulo
     * @return
     */
    Proyecto findByTitulo(String titulo);

}
