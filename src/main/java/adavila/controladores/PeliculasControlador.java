package adavila.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import adavila.modelos.Director;
import adavila.modelos.Pelicula;
import adavila.modelos.Resena;
import adavila.repositorios.DirectorRepositorio;
import adavila.repositorios.PeliculaRepositorio;

@Controller
@RequestMapping ("/peliculas")
public class PeliculasControlador {
	
	@Autowired
   	private PeliculaRepositorio pr;
	
	@Autowired
   	private DirectorRepositorio dr;
	
	@GetMapping ({""})
	public String getPelicula(Model model) {
		model.addAttribute("peliculas", pr.findAll());
		return "peliculas";
	}
	
	@GetMapping("/crear")
	public String crearFormulario(Model model) {
		Pelicula peli = new Pelicula();
		model.addAttribute("pelicula", peli);
		model.addAttribute("directores", dr.findAll());
		return "pelisFormulario";
	}
	
	@PostMapping ("/crear/submit")
	public String submitCrearNueva(@ModelAttribute Pelicula peli) {
		pr.save(peli);
		return "redirect:/peliculas";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id")int id) {
		pr.deleteById(id);
		return "redirect:/peliculas";
	}
	
	@GetMapping ("modificar/{id}")
	public String initEditForm(@PathVariable("id")int id, Model model) {
		Pelicula pelicula = pr.findById(id).orElseThrow();
		model.addAttribute("pelicula", pelicula);
		return "pelisModificar";
	}
	
	@PostMapping("/modificar/submit")
	public String processEditForm(@ModelAttribute Pelicula pelicula) {
		Pelicula original =  pr.findById(pelicula.getId()).orElseThrow();
		
	    original.setTitulo(pelicula.getTitulo());
	    original.setYear(pelicula.getYear());
	    // Buscar si ya existe un director con ese nombre
	    Director existente = dr.findByNombre(pelicula.getDirector().getNombre());

	    if (existente == null) {
	        // No existe → guardar nuevo
	        existente = dr.save(pelicula.getDirector());
	    }

	    // Asignar el director (existente o recién creado) a la película
	    original.setDirector(existente);


	    // Guardar la película
	    pr.save(original);

	    return "redirect:/peliculas"; // o donde quieras redirigir
	}
}
