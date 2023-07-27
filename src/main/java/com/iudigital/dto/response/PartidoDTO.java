package com.iudigital.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartidoDTO {
	
    Long id;

    String nombre;

    int estado;

    LocalDateTime created_at;

    LocalDateTime updated_at;

}
