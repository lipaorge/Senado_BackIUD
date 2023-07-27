package com.iudigital.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iudigital.dto.request.SenadorDTORequest;
import com.iudigital.dto.response.SenadorDTO;
import com.iudigital.exceptions.ErrorDto;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Partido;
import com.iudigital.model.Senador;
import com.iudigital.repository.IPartidoRepository;
import com.iudigital.repository.ISenadorRepository;
import com.iudigital.service.iface.ISenadorService;

@Service
public class SenadorServiceImpl implements ISenadorService {

	// @Autowired
	private ISenadorRepository senadorRepository;
	private IPartidoRepository partidoRepository;
	// private IVotacionRepository votacionRepository;

	@Autowired
	public SenadorServiceImpl(ISenadorRepository senadorRepository, IPartidoRepository partidoRepository) {
		this.senadorRepository = senadorRepository;
		this.partidoRepository = partidoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SenadorDTO> consultarSenadores() throws InternalServerErrorException {

		List<Senador> senadores = senadorRepository.findAll();
		
		if (senadores == null ) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("No hay informacion para listar")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}

		return senadores.stream()
				.map(senador -> SenadorDTO.builder().id(senador.getId()).nombre(senador.getNombre())
						.apellido(senador.getApellido()).departamento(senador.getDepartamento())
						.partidoId(senador.getPartido().getId()).created_at(senador.getCreated_at())
						.updated_at(senador.getUpdated_at()).build())
				.collect(Collectors.toList());

	}

	@Override 
	public SenadorDTO consultarSenadorPorId(Long id) throws InternalServerErrorException {
		System.out.println("senador");
		Senador senador = senadorRepository.findById(id).orElse(null);
		if (senador == null) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Id Senador no Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					); 
			
		}
		Partido partido = partidoRepository.findById(senador.getPartido().getId()).orElse(null);
        
		return SenadorDTO.builder()
				.id(senador.getId())
				.nombre(senador.getNombre())
				.apellido(senador.getApellido())
				.departamento(senador.getDepartamento())
				.partidoId(senador.getPartido().getId())
				.partidoId(partido.getId()).
				build();
	}

	 

	@Override
	public SenadorDTO guardarSenador(SenadorDTORequest senadorDTORequest) throws InternalServerErrorException {

        Senador senador = new Senador();

		// seteamos valores al senador desde el request
		senador.setNombre(senadorDTORequest.getNombre());
		senador.setApellido(senadorDTORequest.getApellido());
		senador.setDepartamento(senadorDTORequest.getDepartamento());

		// buscar el partido por id (request)
		Optional<Partido> partidoOptional = partidoRepository.findById(senadorDTORequest.getPartidoId());

		// validamos si el partido existe
		if (!partidoOptional.isPresent()) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Nombre Partido No Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}

		// seteamos el partido al senador
		senador.setPartido(partidoOptional.get());

		// Otra opcion mas corta
		// senador.setPartido(partidoRepository.findById(senadorDTORequest.getPartidoId()).orElse(null));

		senadorRepository.save(senador);

		return SenadorDTO.builder()
				.id(senador.getId())
				.nombre(senador.getNombre())
				.apellido(senador.getApellido())
				.departamento(senador.getDepartamento())
				.partidoId(senador.getPartido().getId())
				.created_at(senador.getCreated_at())
				.updated_at(senador.getUpdated_at())
				.build();
	}

	@Override
	public SenadorDTO actualizarSenador(Long id, SenadorDTORequest senadorDTORequest) throws InternalServerErrorException {
		Senador senador = senadorRepository.findById(id).orElse(null);

		if (senador == null) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Id Senador no Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		} else {

			senador.setNombre(senadorDTORequest.getNombre());
			senador.setApellido(senadorDTORequest.getApellido());
			senador.setDepartamento(senadorDTORequest.getDepartamento());
			senador.setUpdated_at(LocalDateTime.now());

			// buscar el partido por id (request)
			Optional<Partido> partidoOptional = partidoRepository.findById(senadorDTORequest.getPartidoId());

			// validamos si el partido existe
			if (!partidoOptional.isPresent()) {
				throw new InternalServerErrorException(
						ErrorDto.builder()			
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Nombre Partido No Existe")
						.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
						.date(LocalDateTime.now())
						.build()
						);
				
			}

			// seteamos el partido al senador
			senador.setPartido(partidoOptional.get());

			senadorRepository.save(senador);

			return SenadorDTO.builder().id(senador.getId()).nombre(senador.getNombre()).apellido(senador.getApellido())
					.departamento(senador.getDepartamento()).partidoId(senador.getPartido().getId())
					.created_at(senador.getCreated_at()).updated_at(senador.getUpdated_at()).build();
		}
	}

	@Override
	public Boolean eliminarSenador(Long id) throws InternalServerErrorException {


        Senador senador = senadorRepository.findById(id).orElse(null);

        if (senador != null) {
            senadorRepository.delete(senador);
            return true;
        } else {
        	throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Senador No se puede Eliminar, No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
        }
	}

	@Override
	public List<SenadorDTO> consultarSenadoresPorPartido(Long partido_id) {
		   List<Senador> senadores = senadorRepository.findByPartidoId(partido_id);

	        return senadores.stream()
	                .map(senador -> SenadorDTO.builder()
	                        .id(senador.getId())
	                        .nombre(senador.getNombre())
	                        .apellido(senador.getApellido())
	                        .departamento(senador.getDepartamento())
	                        .partidoId(senador.getPartido().getId())
	                        .created_at(senador.getCreated_at())
	                        .updated_at(senador.getUpdated_at())
	                        .build())
	                .collect(Collectors.toList());
	    }
	

}
