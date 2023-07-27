package com.iudigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iudigital.model.Senador;

@Repository
public interface ISenadorRepository extends JpaRepository<Senador, Long>{
	

    /**
     * Método que busca un senador por su nombre
     * @param nombre
     * @return
     */
    Senador findByNombre(String nombre);
    

    /**
     * Método para mostrar la lista de senadores por partido
     * @param partido_id
     * @return List
     */
    List<Senador> findByPartidoId(Long partido_id);

}
