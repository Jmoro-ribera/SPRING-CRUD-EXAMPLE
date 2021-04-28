package EActa.EActa.repositorio;

import EActa.EActa.modelo.Usuario;
import EActa.EActa.modelo.Rol;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
	@Query("FROM Usuario u WHERE u.nombre = :name")
	List<Usuario> findUsersByName(@Param("name") String name);
	
}
