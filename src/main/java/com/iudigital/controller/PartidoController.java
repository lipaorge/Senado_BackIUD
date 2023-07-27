package com.iudigital.controller;

import java.util.LinkedHashMap;
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

import com.iudigital.dto.request.PartidoDTORequest;
import com.iudigital.dto.response.PartidoDTO;
import com.iudigital.exceptions.BadRequestException;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.service.iface.IPartidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Partidos", description = "Partidos APIs")
@RestController
@RequestMapping("/partidos")

public class PartidoController {
	
	@Autowired
	IPartidoService partidoService;
	
	@Operation(
		      summary = "Listar Partidos",
		      description = "Lista de todos los partidos creados.")
	@GetMapping
    //@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<PartidoDTO>> index() throws InternalServerErrorException{

		return ResponseEntity
				.ok()
				.body(partidoService.consultarPartidos());
	}
	
	@Operation(
		      summary = "Listar Partidos por Id",
		      description = "Lista partidos por Id.")
	@GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<PartidoDTO> show(@PathVariable Long id){
        return ResponseEntity
                .ok()
                .body(
                        partidoService.consultarPartidoPorId(id)
                );
    }
	
	@Operation(
		      summary = "Crear Partido",
		      description = "Guarda informacion partido.")
	@PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PartidoDTO> create(@RequestBody @Valid PartidoDTORequest partidoDTORequest ) throws BadRequestException, InternalServerErrorException{
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(partidoService.guardarPartido(partidoDTORequest));
	}
	
	@Operation(
		      summary = "Actualizar Proyecto por Id",
		      description = "Actualiza proyecto seleccionado por Id.")
	@PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid PartidoDTORequest partidoDTORequest) throws InternalServerErrorException {
        return ResponseEntity
                .ok()
                .body(
                        partidoService.actualizarPartido(id, partidoDTORequest)
                );
    }
	
	@Operation(
		      summary = "Eliminar Partido por Id",
		      description = "Borrar partido seleccionado por Id.")
	@DeleteMapping()
	public String delete(@RequestBody LinkedHashMap<String, Long> body) throws InternalServerErrorException {
		return partidoService.eliminarPartido(body.get("id"));
	}
	
	//@DeleteMapping("/{id}")
    //public ResponseEntity<Boolean> delete(@PathVariable Long id) {
    //    return ResponseEntity
    //            .ok()
    //            .body(
    //                    partidoService.eliminarPartido(id)
    //            );
    //}

}
