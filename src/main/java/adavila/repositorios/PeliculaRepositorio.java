package adavila.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import adavila.modelos.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{

}
