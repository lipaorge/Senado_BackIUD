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

import com.iudigital.dto.request.ProyectoDTORequest;

import com.iudigital.dto.response.ProyectoDTO;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.service.iface.IProyectoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Proyectos", description = "Proyectos APIs")
@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
	
	@Autowired
	IProyectoService proyectoService;
	
	@Operation(
		      summary = "Listar Proyectos",
		      description = "Lista de todos los proyectos creados.")
	@GetMapping
    @ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<ProyectoDTO>> index() throws InternalServerErrorException{
		
		return ResponseEntity
				.ok()
				.body(proyectoService.consultarProyectos());
	}
	
	@Operation(
		      summary = "Listar Proyecto por Id",
		      description = "Lista senaroyectos por Id.")
	@GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ProyectoDTO> show(@PathVariable Long id){
        return ResponseEntity
                .ok()
                .body(
                        proyectoService.consultarProyectoPorId(id)
                );
    }
	
	@Operation(
		      summary = "Crear Proyecto",
		      description = "Guarda informacion proyecto.")
	@PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ProyectoDTO> create(@RequestBody @Valid ProyectoDTORequest proyectoDTORequest ) throws InternalServerErrorException{
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(proyectoService.guardarProyecto(proyectoDTORequest));
	}
	
	@Operation(
		      summary = "Actualizar Proyecto por Id",
		      description = "Actualiza proyecto seleccionado por Id.")
	@PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProyectoDTORequest proyectoDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        proyectoService.actualizarProyecto(id, proyectoDTORequest)
                );
    }
	
	@Operation(
		      summary = "Eliminar Proyecto por Id",
		      description = "Borrar proyecto seleccionado por Id.")
	@DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        proyectoService.eliminarProyecto(id)
                );
    }

}
