package com.iudigital.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SenadorDTO {
	
	Long id;
	
	String nombre;
	
	String apellido;
	
	String departamento;
	
	Long partidoId;
	
	LocalDateTime created_at;
	
	LocalDateTime updated_at;

}
