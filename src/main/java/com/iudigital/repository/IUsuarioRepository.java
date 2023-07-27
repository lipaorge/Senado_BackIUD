package com.iudigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iudigital.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	

    /**
     * Método que busca un correo electrónico en la base de datos
     * @param email
     * @return
     */
    Usuario findByEmail(String email);

    /**
     * Método que busca un usuario por su nombre
     * @param nombre
     * @return
     */
    Usuario findByNombre(String nombre);
    

    /**
     * Método que busca un usuario por su nombre para autenticacion
     * @param nombre
     * @return
     */
	//Usuario findByUsername(String email);

}
