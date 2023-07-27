package com.iudigital.service.iface;

import java.util.List;

import com.iudigital.dto.request.VotacionDTORequest;
import com.iudigital.dto.response.VotacionDTO;
import com.iudigital.exceptions.BadRequestException;
import com.iudigital.exceptions.InternalServerErrorException;

public interface IVotacionService {
	
	 /**
     * Consulta todas Votaciones registrados en la base de datos
     * @return
	 * @throws InternalServerErrorException 
     */
    List<VotacionDTO> consultarVotaciones() throws InternalServerErrorException;

    /**
     * Consulta un Votacion por su id
     * @param id
     * @return
     */
    VotacionDTO consultarVotacionPorId(Long id);

    /**
     * Crea un nuevo votacion en la base de datos
     * @param senadorDTORequest
     * @return
     * @throws InternalServerErrorException 
     */
    VotacionDTO guardarVotacion(VotacionDTORequest votacionDTORequest) throws InternalServerErrorException;

    /**
     * Actualiza un votacion en la base de datos
     * @param id
     * @param senadorDTORequest
     * @return
     * @throws InternalServerErrorException 
     * @throws BadRequestException 
     */
    VotacionDTO actualizarVotacion(Long id, VotacionDTORequest votacionDTORequest) throws InternalServerErrorException, BadRequestException;

    /**
     * Elimina un votacion de la base de datos
     * @param id
     * @throws InternalServerErrorException 
     */
    Boolean eliminarVotacion(Long id) throws InternalServerErrorException;

}
