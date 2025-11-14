package adavila.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import adavila.modelos.Director;

public interface DirectorRepositorio extends JpaRepository<Director, Integer>{
	
		Director findByNombre(String nombre);
	
}
