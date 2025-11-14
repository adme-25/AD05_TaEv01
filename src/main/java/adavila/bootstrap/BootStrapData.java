package adavila.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import adavila.modelos.Director;
import adavila.modelos.Pelicula;
import adavila.modelos.Resena;
import adavila.repositorios.DirectorRepositorio;
import adavila.repositorios.PeliculaRepositorio;
import adavila.repositorios.ResenaRepositorio;
import jakarta.transaction.Transactional;

@Component
public class BootStrapData implements CommandLineRunner{
	@Autowired
	private PeliculaRepositorio pr;
	
	@Autowired
	private DirectorRepositorio dr;
	
	@Autowired
	private ResenaRepositorio rr;
	
	@Transactional
	@Override
	public void run (String... arg0) throws Exception{
	    if (pr.count() > 0) {
	        System.out.println("Datos ya cargados. Saltando inicialización.");
	        return;
	    }
		System.out.println("Cargando datos");
		Director director1 = new Director("John Ford");
		Director director2 = new Director ("Sam Peckinpah");
		
		Pelicula peli1 = new Pelicula("Centauros del desierto", 1956);
		Pelicula peli2 = new Pelicula("La Diligencia", 1939);
		Pelicula peli3 = new Pelicula("Grupo Salvaje", 1969);
		
		Resena resena1 = new Resena("Carlos Boyero", "Muy buena", "Una película muy compleja, muy cabrona, muy hermosa, muy triste.");
		Resena resena2 = new Resena("Fernando Morales", "Muy buena", "Maravillosa historia, considerada por muchos como una auténtica obra de culto.");
		Resena resena3 = new Resena("Carlos Boyero", "Muy buena", "Uno de los mejores westerns de todos los tiempos");
		
		peli1.setDirector(director1);
		peli2.setDirector(director1);
		peli3.setDirector(director2);
		
		resena1.setPelicula(peli1);
		resena2.setPelicula(peli1);
		resena3.setPelicula(peli3);
		
		peli1.getResenas().add(resena1);
		peli1.getResenas().add(resena2);
		peli3.getResenas().add(resena3);
		
		dr.save(director1);
		dr.save(director2);
		pr.save(peli1);
		pr.save(peli2);
		pr.save(peli3);
		rr.save(resena1);
		rr.save(resena2);
		rr.save(resena3);
	}
}
