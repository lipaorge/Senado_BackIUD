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
public class SenadorDTORequest {
	

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    String nombre;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotEmpty(message = "El apellido no puede estar vacio")
    String apellido;

    @NotNull(message = "El departamento no puede ser nulo")
    @NotEmpty(message = "El departamento no puede estar vacio")
    String departamento;

    @NotNull(message = "El partido no puede ser nulo")
    @JsonProperty("partido_id")
    Long partidoId;

    @JsonProperty("created_at")
    LocalDateTime created_at;

    @JsonProperty("updated_at")
    LocalDateTime updated_at;

}
