package adavila.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import adavila.modelos.Pelicula;
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
	
	@GetMapping("/borrar")
	public String borrarFormulario(Model modelo) {
		Pelicula peli = new Pelicula();
		modelo.addAttribute("pelicula", peli);
		modelo.addAttribute("peliculas", pr.findAll());
		return "pelisBorrar";
	}
	
	@PostMapping("/borrar/submit")
	public String borrar(@ModelAttribute Pelicula peli) {
		pr.deleteById(peli.getId());
		return "redirect:/peliculas";
	}
}
