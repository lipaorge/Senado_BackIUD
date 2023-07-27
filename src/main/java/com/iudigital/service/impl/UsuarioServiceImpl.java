package com.iudigital.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iudigital.auth.UserDetailsImpl;
import com.iudigital.dto.request.UsuarioDTORequest;
import com.iudigital.dto.response.UsuarioDTO;
import com.iudigital.exceptions.ErrorDto;
import com.iudigital.exceptions.InternalServerErrorException;
import com.iudigital.model.Rol;
import com.iudigital.model.Usuario;
import com.iudigital.repository.IUsuarioRepository;
import com.iudigital.service.iface.IUsuarioService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@org.springframework.context.annotation.Lazy
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService{
	
	BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
	//@Lazy
//   @Autowired
//    public UsuarioServiceImpl(BCryptPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

	
	
	
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Override
	public List<UsuarioDTO> consultarUsuarios() throws InternalServerErrorException {
		List<Usuario> usuarios = usuarioRepository.findAll();
		if (usuarios == null ) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("No hay informacion para listar")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		}

        return usuarios.stream()
                .map(usuario -> UsuarioDTO.builder()
                		.id(usuario.getId())
                		.nombre(usuario.getNombre())
                		.email(usuario.getEmail())
                		.estado(usuario.getEstado())
                		.created_at(usuario.getCreated_at())
                		.updated_at(usuario.getUpdated_at())
                		.rolId(usuario.getRoles().get(0).getId())
                		.build())
                .collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO consultarUsuarioPorId(Long id) {
		
		Usuario usuario = usuarioRepository.findById(id).orElse(null);

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .estado(usuario.getEstado())
                .created_at(usuario.getCreated_at())
                .updated_at(usuario.getUpdated_at())
                .rolId(usuario.getRoles().get(0).getId())
                .build();

       	}

	@Override
	public UsuarioDTO consultarUsuarioPorEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO guardarUsuario(UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException {

        Usuario usuario;
        Rol role = new Rol();

        role.setId(2L); // Se asigna el rol de usuario normal 2 Long (2L)

        usuario = usuarioRepository.findByEmail(usuarioDTORequest.getEmail());

        if (usuario != null) 
        {
        	throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Correo Usuario ya Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
        }

        usuario = new Usuario();
        
        log.info("pasword {}", bpe.encode(usuarioDTORequest.getPassword()));
        // Se convierte el DTO a un objeto de tipo Usuario
        usuario.setNombre(usuarioDTORequest.getNombre());
        usuario.setEmail(usuarioDTORequest.getEmail());
        usuario.setPassword(bpe.encode(usuarioDTORequest.getPassword()));
        usuario.setEstado(1);
        usuario.setRoles(Collections.singletonList(role));

        // Se guarda el usuario en la base de datos
        usuario = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
        		.id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .estado(usuario.getEstado())
                .created_at(usuario.getCreated_at())
                .updated_at(usuario.getUpdated_at())
                .rolId(usuario.getRoles().get(0).getId())
                .build();
    }


	@Override
	public UsuarioDTO actualizarUsuario(Long id, UsuarioDTORequest usuarioDTORequest) throws InternalServerErrorException {
		
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		
		if (usuario == null) {
			throw new InternalServerErrorException(
					ErrorDto.builder()			
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Id Usuario no Existe")
					.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
					.date(LocalDateTime.now())
					.build()
					);
			
		} else {
			usuario.setNombre(usuarioDTORequest.getNombre());
	        usuario.setEmail(usuarioDTORequest.getEmail());
	        usuario.setPassword(usuarioDTORequest.getPassword());
	        
	        usuario = usuarioRepository.save(usuario);
	        return UsuarioDTO.builder()
	        		.id(usuario.getId())
	                .nombre(usuario.getNombre())
	                .email(usuario.getEmail())
	                .estado(usuario.getEstado())
	                .created_at(usuario.getCreated_at())
	                .updated_at(usuario.getUpdated_at())
	                .rolId(usuario.getRoles().get(0).getId())
	                .build();

		}
		

    
	}

	@Override
	public Boolean eliminarUsuario(Long id) throws InternalServerErrorException {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
        	usuarioRepository.delete(usuario);
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
            //log.error("Error de login, no existe usuario: "+ usuario);
            throw new UsernameNotFoundException("Error de login, no existe usuario: "+ email);
	    }

//        return new User(usuario.getEmail(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
//	
//	}
//	    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Rol> roles) {
//	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
//	    }

		return new UserDetailsImpl(usuario);
	}
}
