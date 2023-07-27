package com.iudigital.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Usuario implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    String nombre;

    @Column(name = "email", nullable = false, length = 120)
    String email;

    @Column(name = "password", nullable = false, length = 250)
    String password;

    @Column(name = "estado", nullable = false, length = 1, columnDefinition = "int default 1")
    int estado;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updated_at;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_usuarios",
			joinColumns = {@JoinColumn(name = "usuarios_id")},
			inverseJoinColumns = {@JoinColumn(name= "roles_id")})
    
    List<Rol> roles;

    @PrePersist
    public void prePersist() {
    	if (Objects.isNull(created_at)) {
            created_at = LocalDateTime.now();
        }
    }

}
