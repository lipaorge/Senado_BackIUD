package com.iudigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iudigital.dto.request.VotacionDTORequest;
import com.iudigital.dto.response.SenadorDTO;
import com.iudigital.dto.response.VotacionDTO;
import com.iudigital.exceptions.BadRequestException;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Votacion;
import com.iudigital.service.iface.IVotacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * @author lipao
 *
 */
@RestController
@RequestMapping("/votaciones")
@Tag(name = "Votaciones", description = "Votaciones APIs")


public class VotacionController {
	
	@Autowired
	IVotacionService votacionService;
	
	@Operation(
		      summary = "Listar todas las votaciones",
		      description = "Lista las votaciones obtenidas en el senado.")
	@GetMapping
	public ResponseEntity<List<VotacionDTO>> index() throws InternalServerErrorException{
		return ResponseEntity
				.ok()
				.body(
						votacionService.consultarVotaciones());
	}

	/**
     * Método que retorna una votacion por su id
     * @param id
     * @return VotacionDTO
     * @throws InternalServerErrorException 
     */

	@Operation(
		      summary = "Listar Votaciones Id",
		      description = "Lista las votaciones por Id.")
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<VotacionDTO> show(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        votacionService.consultarVotacionPorId(id)
                );
    }
    /**
     * Método que crea una Votacion nueva y almacenarlo en la base de datos
     * @param voacionDTORequest
     * @return votacionDTO
     * @throws InternalServerErrorException 
     */
	

	@Operation(
		      summary = "Guardar todas las votaciones",
		      description = "Guarda las votaciones realiada.")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<VotacionDTO> create(
            @RequestBody @Valid VotacionDTORequest votacionDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        votacionService.guardarVotacion(votacionDTORequest)
                );
    }
    
    
    /**
     * @param id
     * @param votacionDTORequest
     * @return
     * @throws InternalServerErrorException 
     * @throws BadRequestException 
     */

	@Operation(
		      summary = "Actualizar la votacion por Id",
		      description = "Actualiza la votacion seleccionada.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<VotacionDTO> update(
            @PathVariable Long id,
            @RequestBody VotacionDTORequest votacionDTORequest) throws InternalServerErrorException, BadRequestException {
        return ResponseEntity
                .ok()
                .body(
                        votacionService.actualizarVotacion(id, votacionDTORequest)
                );
    }
    
    /**
     * @param id
     * @return
     * @throws InternalServerErrorException
     */

	@Operation(
		      summary = "Borrar la votacion por Id",
		      description = "Borra la votacione seleccionada.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        votacionService.eliminarVotacion(id)
                );
    }
}
