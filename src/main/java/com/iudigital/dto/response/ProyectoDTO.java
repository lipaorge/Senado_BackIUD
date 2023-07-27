package com.iudigital.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProyectoDTO {
	

    Long id;

    String titulo;

    String descripcion;

    String urlArchivo;

    LocalDateTime created_at;

    LocalDateTime updated_at;

}
