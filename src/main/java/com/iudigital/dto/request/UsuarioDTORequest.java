package com.iudigital.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsuarioDTORequest {
	

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    String nombre;

    @NotNull(message = "El email no puede ser nulo")
    @NotEmpty(message = "El email no puede estar vacio")
    String email;


    @NotNull(message = "La contraseña no puede ser nula")
    @NotEmpty(message = "La contraseña no puede estar vacia")
    String password;
    
    int estado;
    

    @JsonProperty("created_at")
    LocalDateTime created_at;


    @JsonProperty("updated_at")
    LocalDateTime updated_at; 
    

}
