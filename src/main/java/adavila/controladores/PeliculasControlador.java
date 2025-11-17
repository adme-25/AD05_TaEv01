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
		//Recogemos la pelicula a modificar por su id 
		//y pasarle los cambios efectuados en el modelo
		//y conservar los que no se editan
		Pelicula original =  pr.findById(pelicula.getId()).orElseThrow();
		
	    original.setTitulo(pelicula.getTitulo());
	    original.setYear(pelicula.getYear());
	    
	    // Comprobamos si el director recogido existe y si no lo creamos a partir del modelo
	    Director existente = dr.findByNombre(pelicula.getDirector().getNombre());
	    if (existente == null) {
	        existente = dr.save(pelicula.getDirector());
	    }
	    original.setDirector(existente);

	    pr.save(original);

	    return "redirect:/peliculas"; 
	}
}
