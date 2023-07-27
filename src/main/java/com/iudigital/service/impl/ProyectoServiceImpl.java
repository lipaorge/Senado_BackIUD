package com.iudigital.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iudigital.dto.request.ProyectoDTORequest;
import com.iudigital.dto.response.ProyectoDTO;
import com.iudigital.exceptions.ErrorDto;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Partido;
import com.iudigital.model.Proyecto;
import com.iudigital.repository.IProyectoRepository;
import com.iudigital.service.iface.IProyectoService;


@Service
public class ProyectoServiceImpl implements IProyectoService{
	
	@Autowired
	private IProyectoRepository proyectoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ProyectoDTO> consultarProyectos() throws InternalServerErrorException {
		List<Proyecto> proyectos = proyectoRepository.findAll();
		if (proyectos == null ) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("No hay informacion para listar")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}
        return proyectos.stream()
        		.map(proyecto -> ProyectoDTO.builder()
        				.id(proyecto.getId())
        				.titulo(proyecto.getTitulo())
        				.descripcion(proyecto.getDescripcion())
        				.urlArchivo(proyecto.getUrlArchivo())
        				.created_at(proyecto.getCreated_at())
        				.updated_at(proyecto.getUpdated_at())
        				.build())
        				.collect(Collectors.toList());
        		
	}

	@Override
	public ProyectoDTO consultarProyectoPorId(Long id) {
	    Proyecto proyecto = proyectoRepository.findById(id).orElse(null);

        return ProyectoDTO.builder()
                .id(proyecto.getId())
                .titulo(proyecto.getTitulo())
                .descripcion(proyecto.getDescripcion())
                .urlArchivo(proyecto.getUrlArchivo())
                .created_at(proyecto.getCreated_at())
                .updated_at(proyecto.getUpdated_at())
                .build();
    
	}

	@Override
	@Transactional
	@Secured("ADMIN")
	public ProyectoDTO guardarProyecto(ProyectoDTORequest proyectoDTORequest) throws InternalServerErrorException {
		Proyecto proyecto = proyectoRepository.findByTitulo(proyectoDTORequest.getTitulo());
		
		if (proyecto != null) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Titulo Proyecto ya existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}
		
		
		proyecto = new Proyecto();
		proyecto.setTitulo(proyectoDTORequest.getTitulo());
		proyecto.setDescripcion(proyectoDTORequest.getDescripcion());
		proyecto.setUrlArchivo(proyectoDTORequest.getUrlArchivo());
		
		proyecto = proyectoRepository.save(proyecto);
		return ProyectoDTO.builder()
				.id(proyecto.getId())
				.titulo(proyecto.getTitulo())
				.descripcion(proyecto.getDescripcion())
				.urlArchivo(proyecto.getUrlArchivo())
				.created_at(proyecto.getCreated_at())
				.updated_at(proyecto.getUpdated_at())
				.build();
	}

	@Override
	public ProyectoDTO actualizarProyecto(Long id, ProyectoDTORequest proyectoDTORequest) throws InternalServerErrorException {
		

		Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
		
		if (proyecto != null) {
			
			proyecto.setTitulo(proyectoDTORequest.getTitulo());
			proyecto.setDescripcion(proyectoDTORequest.getDescripcion());
			proyecto.setUrlArchivo(proyectoDTORequest.getUrlArchivo());

			proyecto = proyectoRepository.save(proyecto);

	        return ProyectoDTO.builder()
	        		.id(proyecto.getId())
					.titulo(proyecto.getTitulo())
					.descripcion(proyecto.getDescripcion())
					.urlArchivo(proyecto.getUrlArchivo())
					.created_at(proyecto.getCreated_at())
					.updated_at(proyecto.getUpdated_at())
					.build();
			
		} else {

			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Proyecto No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);

		}

    
	}

	//@Override
	//public String eliminarProyecto(Long id) {
		
		 //Proyecto proyecto = proyectoRepository.findById(id).orElse(null);

        //proyectoRepository.deleteById(id);
		//return "Proyecto Eliminado";
		
	//}
	
	@Override
    public Boolean eliminarProyecto(Long id) throws InternalServerErrorException {

        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);

        if (proyecto != null) {
            proyectoRepository.delete(proyecto);
            return true;
        } else {
        	throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Proyecto No se puede Eliminar, No existe ID = " + id)
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
        }
    }
//    public Boolean eliminarProyecto(Long id) {
//
//        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
//
//        if (proyecto != null) {
//            proyectoRepository.delete(proyecto);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
