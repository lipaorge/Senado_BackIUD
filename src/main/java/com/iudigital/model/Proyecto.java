package com.iudigital.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;



@Entity
@Table(name = "proyectos")
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Proyecto implements Serializable{
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    String titulo;

    @Column(name = "descripcion", nullable = false, length = 250)
    String descripcion;

    @Column(name = "url_archivo", nullable = false, length = 250)
    String urlArchivo;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
    	if (Objects.isNull(created_at)) {
            created_at = LocalDateTime.now();
        }
    }

}
