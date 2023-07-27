package com.iudigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iudigital.dto.request.UsuarioDTORequest;
import com.iudigital.dto.response.UsuarioDTO;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.service.iface.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * @author lipao
 *
 */
@RestController
@RequestMapping("/usuarios")

@Tag(name = "Usuarios", description = "Usuarios APIs")
public class UsuarioController {
	
   // @Autowired
    //private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	IUsuarioService usuarioService;

	
	/**
     * Método que retorna todos los usuarios
     * @return
	 * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Listar Usuarios",
		      description = "Lista de todos los usuarios del sistema.")
	@GetMapping
    //@ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<UsuarioDTO>> index() throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        usuarioService.consultarUsuarios()
                );
    }
    
	@Operation(
		      summary = "Listar Usarios por Id",
		      description = "Lista de todos los usuarios del sistema por Id.")
	
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UsuarioDTO> show(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(
                        usuarioService.consultarUsuarioPorId(id)
                );
    }

	@Operation(
		      summary = "Crear Usario",
		      description = "Guarda un usuario nuevo del sistema.")
	
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UsuarioDTO> create(
            @RequestBody @Valid UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        usuarioService.guardarUsuario(usuarioDTORequest)
                );
    }
    
	@Operation(
		      summary = "Actualizar Usario",
		      description = "Actualiza un usuario del sistema.")
	
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
    		@PathVariable Long id,
    		@RequestBody @Valid UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException{
    	
    	return ResponseEntity
    			.ok()
    			.body(
    					usuarioService.actualizarUsuario(id, usuarioDTORequest));
    }
    
	@Operation(
		      summary = "Eliminar Usario",
		      description = "Borrar el usuario seleccionado del sistema.")
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        usuarioService.eliminarUsuario(id)
                );
    }
}
