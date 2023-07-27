package com.iudigital.service.iface;

import java.util.List;

import com.iudigital.dto.request.ProyectoDTORequest;
import com.iudigital.dto.response.ProyectoDTO;
import com.iudigital.exceptions.InternalServerErrorException;

public interface IProyectoService {
	
    /**
     * Consulta todos los proyectos registrados en la base de datos
     * @return
     * @throws InternalServerErrorException 
     */
    List<ProyectoDTO> consultarProyectos() throws InternalServerErrorException;

    /**
     * Consulta un proyecto por su id
     * @param id
     * @return
     */
    ProyectoDTO consultarProyectoPorId(Long id);

    /**
     * Crear un nuevo proyecto en la base de datos
     * @param proyectoDTORequest
     * @return
     * @throws InternalServerErrorException 
     */
    ProyectoDTO guardarProyecto(ProyectoDTORequest proyectoDTORequest) throws InternalServerErrorException;

    /**
     * Actualiza un proyecto en la base de datos
     * @param id
     * @param proyectoDTORequest
     * @return
     * @throws InternalServerErrorException 
     */
    ProyectoDTO actualizarProyecto(Long id, ProyectoDTORequest proyectoDTORequest) throws InternalServerErrorException;

    /**
     * Elimina un proyecto de la base de datos
     * @param id
     * @throws InternalServerErrorException 
     */
    Boolean eliminarProyecto(Long id) throws InternalServerErrorException;


}
