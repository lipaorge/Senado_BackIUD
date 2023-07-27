package com.iudigital.service.iface;

import java.util.List;

import com.iudigital.dto.request.PartidoDTORequest;
import com.iudigital.dto.response.PartidoDTO;
import com.iudigital.exceptions.BadRequestException;
import com.iudigital.exceptions.InternalServerErrorException;


public interface IPartidoService {
	

    /**
     * Consulta todos los partidos registrados en la base de datos
     * @return
     * @throws InternalServerErrorException 
     */
    List<PartidoDTO> consultarPartidos() throws InternalServerErrorException;

    /**
     * Consulta un partido por su id
     * @param id
     * @return
     */
    PartidoDTO consultarPartidoPorId(Long id);

    /**
     * Crea un nuevo partido en la base de datos
     * @param partidoDTO
     * @return
     * @throws BadRequestException 
     * @throws InternalServerErrorException 
     */
    PartidoDTO guardarPartido(PartidoDTORequest partidoDTORequest) throws BadRequestException, InternalServerErrorException;

    /**
     * Actualiza un partido en la base de datos
     * @param id
     * @param partidoDTO
     * @return
     * @throws InternalServerErrorException 
     */
    PartidoDTO actualizarPartido(Long id, PartidoDTORequest partidoDTORequest) throws InternalServerErrorException;

    /**
     * Elimina un partido de la base de datos
     * @param id
     * @throws InternalServerErrorException 
     */
    String eliminarPartido(Long id) throws InternalServerErrorException;

}
