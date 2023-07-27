package com.iudigital.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VotacionDTORequest {
	

	//@NotNull(message = "El Proyecto no puede ser nulo")
	//@NotEmpty(message = "El Proyecto no puede estar vacio")
	@JsonProperty("proyectos_id")
	Long proyectoId;
	

	//@NotNull(message = "El Senador no puede ser nulo")
	//@NotEmpty(message = "El Senador no puede estar vacio")
	@JsonProperty("senadores_id")
	Long senadorId;
	

	//@NotNull(message = "El Usuario no puede ser nulo")
	//@NotEmpty(message = "El Usuario no puede estar vacio")
	@JsonProperty("usuarios_id")
	Long usuarioId;
	

	//@NotNull(message = "El Voto no puede ser nulo")
	//@NotEmpty(message = "El Voto no puede estar vacio")
	int voto;
	
	@JsonProperty("created_at")
	LocalDateTime created_at;

}
