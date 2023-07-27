package com.iudigital.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioDTO {
	
	Long id;
	
    String nombre;

    String email;

    String password;
    
    int estado;

    LocalDateTime created_at;

    LocalDateTime updated_at; 
    
    Long rolId;

}
