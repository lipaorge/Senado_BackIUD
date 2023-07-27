package com.iudigital.service.iface;

import java.util.List;

import com.iudigital.dto.request.SenadorDTORequest;
import com.iudigital.dto.response.SenadorDTO;
import com.iudigital.exceptions.InternalServerErrorException;

public interface ISenadorService {
	
	 /**
     * Consulta todos los senadores registrados en la base de datos
     * @return
	 * @throws InternalServerErrorException 
     */
    List<SenadorDTO> consultarSenadores() throws InternalServerErrorException;

    /**
     * Consulta un senador por su id
     * @param id
     * @return
     * @throws InternalServerErrorException 
     */
    SenadorDTO consultarSenadorPorId(Long id) throws InternalServerErrorException;
    

    /**
     * Mostrar la lista de senadores por partido
     * @param partido_id
     * @return List
     */
    List<SenadorDTO> consultarSenadoresPorPartido(Long partido_id);

    /**
     * Crea un nuevo senador en la base de datos
     * @param senadorDTORequest
     * @return
     * @throws InternalServerErrorException 
     */
    SenadorDTO guardarSenador(SenadorDTORequest senadorDTORequest) throws InternalServerErrorException;

    /**
     * Actualiza un senador en la base de datos
     * @param id
     * @param senadorDTORequest
     * @return
     * @throws InternalServerErrorException 
     */
    SenadorDTO actualizarSenador(Long id, SenadorDTORequest senadorDTORequest) throws InternalServerErrorException;

    /**
     * Elimina un senador de la base de datos
     * @param id
     * @throws InternalServerErrorException 
     */
    Boolean eliminarSenador(Long id) throws InternalServerErrorException;


}
