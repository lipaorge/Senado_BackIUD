package com.iudigital.service.iface;

import java.util.List;

import com.iudigital.dto.request.UsuarioDTORequest;
import com.iudigital.dto.response.UsuarioDTO;
import com.iudigital.exceptions.InternalServerErrorException;

public interface IUsuarioService {
	

    /**
     * Consulta todos los usuarios registrados en la base de datos
     * @return List<UsuarioDTO>
     * @throws InternalServerErrorException 
     */
    List<UsuarioDTO> consultarUsuarios() throws InternalServerErrorException;

    /**
     * Consulta un usuario por su id
     * @param id
     * @return UsuarioDTO
     */
    UsuarioDTO consultarUsuarioPorId(Long id);

    /**
     * Consulta un usuario por su correo electronico
     * @param email
     * @return UsuarioDTO
     */
    UsuarioDTO consultarUsuarioPorEmail(String email);

    /**
     * Crear un nuevo usuario en la base de datos
     * @param usuarioDTORequest
     * @return UsuarioDTO
     * @throws InternalServerErrorException 
     */
    UsuarioDTO guardarUsuario(UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException;

    /**
     * Actualiza un usuario en la base de datos
     * @param id
     * @param usuarioDTORequest
     * @return UsuarioDTO
     * @throws InternalServerErrorException 
     */
    UsuarioDTO actualizarUsuario(Long id, UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException;

    /**
     * Elimina un usuario de la base de datos
     * @param id
     * @return Boolean
     * @throws InternalServerErrorException 
     */
    Boolean eliminarUsuario(Long id) throws InternalServerErrorException;

}
