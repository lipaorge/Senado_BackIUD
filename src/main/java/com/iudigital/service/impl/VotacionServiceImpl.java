package com.iudigital.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iudigital.dto.request.VotacionDTORequest;
import com.iudigital.dto.response.VotacionDTO;
import com.iudigital.exceptions.ErrorDto;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Proyecto;
import com.iudigital.model.Senador;
import com.iudigital.model.Usuario;
import com.iudigital.model.Votacion;
import com.iudigital.repository.IProyectoRepository;
import com.iudigital.repository.ISenadorRepository;
import com.iudigital.repository.IUsuarioRepository;
import com.iudigital.repository.IVotacionRepository;
import com.iudigital.service.iface.IVotacionService;


@Service
public class VotacionServiceImpl implements IVotacionService{
	
	@Autowired
    private ISenadorRepository senadorRepository;
	
	@Autowired
    private IUsuarioRepository usuarioRepository;
	
	@Autowired
    private IVotacionRepository votacionRepository;
	
	@Autowired
	private IProyectoRepository proyectoRepository;

	@Override
	public List<VotacionDTO> consultarVotaciones() throws InternalServerErrorException {
		
		List<Votacion> votaciones = votacionRepository.findAll();
		
		if (votaciones == null ) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("No hay informacion para listar")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}
		
		return votaciones.stream()
				.map(votacion -> VotacionDTO.builder()
						.id(votacion.getId())
						.proyectoId(votacion.getProyecto().getId())
						.senadorId(votacion.getSenador().getId())
						.usuarioId(votacion.getUsuario().getId())
						.created_at(votacion.getCreated_at())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public VotacionDTO consultarVotacionPorId(Long id) {

        Votacion votacion = votacionRepository.findById(id).orElse(null);

        return VotacionDTO.builder()
                .id(votacion.getId())
                .proyectoId(votacion.getProyecto().getId())
                .senadorId(votacion.getSenador().getId())
                .usuarioId(votacion.getUsuario().getId())
                .voto(votacion.getVoto())
                .created_at(votacion.getCreated_at())
                .build();
		
	}

	@Override
	public VotacionDTO guardarVotacion(VotacionDTORequest votacionDTORequest) throws InternalServerErrorException {
		Votacion votacion = new Votacion();
		
		Optional<Proyecto> proyectoOptional = proyectoRepository.findById(votacionDTORequest.getProyectoId());

		Optional<Senador> senadorOptional = senadorRepository.findById(votacionDTORequest.getSenadorId());
		

		Optional<Usuario> usuOptional = usuarioRepository.findById(votacionDTORequest.getUsuarioId());
		if (!proyectoOptional.isPresent()) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Proyecto No Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
		}else if (!senadorOptional.isPresent()) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Senador No Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}else if (!usuOptional.isPresent()) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Usuario No Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}

		
		votacion.setProyecto(proyectoOptional.get());
		votacion.setSenador(senadorOptional.get());
		votacion.setUsuario(usuOptional.get());
		votacion.setVoto(votacionDTORequest.getVoto());
		votacion.setCreated_at(votacionDTORequest.getCreated_at());
		
		votacionRepository.save(votacion);
		
		return VotacionDTO.builder()
				.id(votacion.getId())
				.proyectoId(votacion.getProyecto().getId())
				.senadorId(votacion.getSenador().getId())
				.usuarioId(votacion.getUsuario().getId())
				.created_at(votacion.getCreated_at())
				.build();
	}

	@Override
	public VotacionDTO actualizarVotacion(Long id, VotacionDTORequest votacionDTORequest) throws InternalServerErrorException {

		Votacion votacion = votacionRepository.findById(id).orElse(null);
		
		if (votacion == null) {

			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Id Votacion No Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
		}else {
			Optional<Proyecto> proyectoOptional = proyectoRepository.findById(votacionDTORequest.getProyectoId());

			Optional<Senador> senadorOptional = senadorRepository.findById(votacionDTORequest.getSenadorId());

			Optional<Usuario> usuOptional = usuarioRepository.findById(votacionDTORequest.getUsuarioId());
			
			if (!proyectoOptional.isPresent() || !senadorOptional.isPresent() || !usuOptional.isPresent()) {

				throw new InternalServerErrorException(
						ErrorDto.builder()			
						.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Usuario o Proyecto o Senadro No Existe")
						.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
						.date(LocalDateTime.now())
						.build()
						);
			}
			votacion.setProyecto(proyectoOptional.get());
			votacion.setSenador(senadorOptional.get());
			votacion.setUsuario(usuOptional.get());
			votacion.setVoto(votacionDTORequest.getVoto());
			votacion.setCreated_at(votacionDTORequest.getCreated_at());
			
			votacionRepository.save(votacion);
			
			return VotacionDTO.builder()
					.id(votacion.getId())
					.proyectoId(votacion.getProyecto().getId())
					.senadorId(votacion.getSenador().getId())
					.usuarioId(votacion.getUsuario().getId())
					.created_at(votacion.getCreated_at())
					.build();

			
		}
		
	}

	@Override
	public Boolean eliminarVotacion(Long id) throws InternalServerErrorException {
		Votacion votacion = votacionRepository.findById(id).orElse(null);
		
		if (votacion != null) {

			votacionRepository.deleteById(id);
			return true;
			
		} else {

			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Usuario No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);

		}
	}

}
