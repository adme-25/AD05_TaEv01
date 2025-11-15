package adavila.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import adavila.modelos.Resena;
import adavila.repositorios.PeliculaRepositorio;
import adavila.repositorios.ResenaRepositorio;

@Controller
@RequestMapping ("/resenas")
public class ResenasControlador {
	@Autowired
   	private ResenaRepositorio rr;
	
	@Autowired
   	private PeliculaRepositorio pr;
	
	@GetMapping ({""})
	public String getPelicula(Model model) {
		model.addAttribute("resenas", rr.findAll());
		return "resenas";
	}
	
	@GetMapping("/crear")
	public String crearFormulario(Model model) {
		Resena resena = new Resena();
		model.addAttribute("resena", resena);
		model.addAttribute("peliculas", pr.findAll());
		
		return "resenasForm";
	}
	
	@PostMapping ("/crear/submit")
	public String submitCrearNueva(@ModelAttribute Resena resena) {
		rr.save(resena);
		return "redirect:/resenas";
	}
	
	@GetMapping ("modificar/{id}")
	public String initEditForm(@PathVariable("id")int id, Model model) {
		Resena resena = rr.findById(id).orElseThrow();
		model.addAttribute("resena", resena);
		return "resenasForm";
	}
}
