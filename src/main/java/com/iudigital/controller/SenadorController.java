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

import com.iudigital.dto.request.SenadorDTORequest;
import com.iudigital.dto.response.SenadorDTO;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.service.iface.ISenadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Senadores", description = "Senadores APIs")
@RestController
@RequestMapping("/senadores")
public class SenadorController {
	
	@Autowired
    ISenadorService senadorService;

    /**
     * Método que retorna todos los senadores
     * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Listar Senadores",
		      description = "Lista de todos los senadores creados.")
	@GetMapping
    public ResponseEntity<List<SenadorDTO>> index() throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        senadorService.consultarSenadores()
                );
    }

    /**
     * Método que retorna un senador por su id
     * @param id
     * @return SenadorDTO
     * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Listar Senador por Id",
		      description = "Lista senador por Id.")
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<SenadorDTO> show(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        senadorService.consultarSenadorPorId(id)
                );
    }

    
    /**
     * Método que retorna la lista de senadores por partido
     * @param partido_id
     * @return List<SenadorDTO>
     */
	@Operation(
		      summary = "Listar Senadores por Partido",
		      description = "Lista senadores por Id partido.")
    @GetMapping("/show/{partido_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<SenadorDTO>> showByPartido(
            @PathVariable Long partido_id) {
        return ResponseEntity
                .ok()
                .body(
                        senadorService.consultarSenadoresPorPartido(partido_id)
                );
    }
    /**
     * Método que crea un senador nuevo y almacenarlo en la base de datos
     * @param senadorDTORequest
     * @return SenadorDTO
     * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Crear Senador",
		      description = "Guarda senador por Id.")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<SenadorDTO> create(
            @RequestBody @Valid SenadorDTORequest senadorDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        senadorService.guardarSenador(senadorDTORequest)
                );
    }

    /**
     * Método que actualiza un senador en la base de datos
     * @param id
     * @param senadorDTORequest
     * @return SenadorDTO
     * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Actualizar Senador por Id",
		      description = "Actualiza senador seleccionado por Id.")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<SenadorDTO> update(
            @PathVariable Long id,
            @RequestBody SenadorDTORequest senadorDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        senadorService.actualizarSenador(id, senadorDTORequest)
                );
    }

    /**
     * Método que elimina un senador de la base de datos
     * @param id
     * @return Boolean
     * @throws InternalServerErrorException 
     */
	@Operation(
		      summary = "Eliminar Senador por Id",
		      description = "Borrar senador seleccionado por Id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        senadorService.eliminarSenador(id)
                );
    }

}
