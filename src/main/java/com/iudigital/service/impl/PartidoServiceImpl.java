package com.iudigital.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iudigital.dto.request.PartidoDTORequest;
import com.iudigital.dto.response.PartidoDTO;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Partido;
import com.iudigital.repository.IPartidoRepository;
import com.iudigital.service.iface.IPartidoService;
import com.iudigital.exceptions.BadRequestException;
import com.iudigital.exceptions.ErrorDto;

@Service
public class PartidoServiceImpl implements IPartidoService{
	
	@Autowired
	private IPartidoRepository partidoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PartidoDTO> consultarPartidos() throws InternalServerErrorException {
		List<Partido> partidos = partidoRepository.findAll();
		if (partidos == null ) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("No hay informacion para listar")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}
		   return partidos.stream()
	                .map(partido -> PartidoDTO.builder()
	                        .id(partido.getId())
	                        .nombre(partido.getNombre())
	                        .estado(partido.getEstado())
	                        .created_at(partido.getCreated_at())
	                        .updated_at(partido.getUpdated_at())
	                        .build())
	                .collect(Collectors.toList());

	    }

	@Override
	public PartidoDTO consultarPartidoPorId(Long id) {
		   Partido partido = partidoRepository.findById(id).orElse(null);
		   
		

	        return PartidoDTO.builder()
	                .id(partido.getId())
	                .nombre(partido.getNombre())
	                .estado(partido.getEstado())
	                .created_at(partido.getCreated_at())
	                .updated_at(partido.getUpdated_at())
	                .build();
	    
	}

	@Override
	public PartidoDTO guardarPartido(PartidoDTORequest partidoDTORequest) throws InternalServerErrorException {
		Partido partido = partidoRepository.findByNombre(partidoDTORequest.getNombre());
		
		if (partido != null) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Nombre Partido ya existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}
		
		partido = new Partido();
		
		partido.setNombre(partidoDTORequest.getNombre());
		partido.setEstado(partidoDTORequest.getEstado());
				
		partido = partidoRepository.save(partido);
		
		return PartidoDTO.builder()
				.id(partido.getId())
				.nombre(partido.getNombre())
				.estado(partido.getEstado())
				.created_at(partido.getCreated_at())
				.updated_at(partido.getUpdated_at())
				.build();
				
	}

	@Override
	public PartidoDTO actualizarPartido(Long id, PartidoDTORequest partidoDTORequest) throws InternalServerErrorException {
		Partido partido = partidoRepository.findById(id).orElse(null);
		
		if (partido != null) {

	        partido.setNombre(partidoDTORequest.getNombre());
	        partido.setEstado(partidoDTORequest.getEstado());

	        partido = partidoRepository.save(partido);

	        return PartidoDTO.builder()
	                .id(partido.getId())
	                .nombre(partido.getNombre())
	                .estado(partido.getEstado())
	                .created_at(partido.getCreated_at())
	                .updated_at(partido.getUpdated_at())
	                .build();
	    
			
		}else {

			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Partido No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
		}

	}
	
	@Override
	public String eliminarPartido(Long id) throws InternalServerErrorException {

		Partido partidoBorrar = partidoRepository.findById(id).orElse(null);
		if (partidoBorrar != null) {

			partidoRepository.delete(partidoBorrar);

			return "Partido Eliminado";
			
		} else {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Partido No se puede Eliminar, No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
		}
		

//	public String eliminarPartido(Long id) {
//
//		Partido partidoBorrar = partidoRepository.findById(id).orElse(null);
//		if (partidoBorrar != null) {
//
//			partidoRepository.delete(partidoBorrar);
//
//			return "Partido Eliminado";
//			
//		} else {
//			return "No se puede Eliminar Partido ";
//		}
		
		
	}
	

}
