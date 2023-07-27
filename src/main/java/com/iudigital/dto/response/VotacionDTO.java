package com.iudigital.dto.response;

import java.time.LocalDateTime;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VotacionDTO {
	
	Long id;
	
	Long proyectoId;
	
	Long senadorId;
	
	Long usuarioId;
	
	int voto;
	
	LocalDateTime created_at;
	
	
}
