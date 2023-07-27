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
public class PartidoDTORequest {
	

    @NotNull(message = "El titulo no puede ser nulo")
    @NotEmpty(message = "El titulo no puede estar vacio")
    String nombre;


    int estado;

    @JsonProperty("created_at")
    LocalDateTime created_at;


    @JsonProperty("updated_at")
    LocalDateTime updated_at; 

}
