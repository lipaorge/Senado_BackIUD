package com.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iudigital.model.Votacion;

@Repository
public interface IVotacionRepository extends JpaRepository<Votacion, Long>{
	

    /**
     * Método que busca una votación por proyecto
     * @param proyecto_id
     * @return
     */
    Votacion findByProyectoId(Long proyecto_id);

    /**
     * Método que busca una votación por senador
     * @param senador_id
     * @return
     */
    Votacion findBySenadorId(Long senador_id);

    /**
     * Método que busca una votación por proyecto y senador
     * @param proyecto_id
     * @param senador_id
     * @return
     */
    Votacion findByProyectoIdAndSenadorId(Long proyecto_id, Long senador_id);

    /**
     * Método que busca una votación por usuario
     * @param usuario_id
     * @return
     */
    Votacion findByUsuarioId(Long usuario_id);

}
