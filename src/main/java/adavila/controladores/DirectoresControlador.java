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
import adavila.repositorios.DirectorRepositorio;

@Controller
@RequestMapping ("/directores")
public class DirectoresControlador {
	@Autowired
   	private DirectorRepositorio dr;
	
	@GetMapping ({""})
	public String getPelicula(Model model) {
		model.addAttribute("directores", dr.findAll());
		return "directores";
	}
	@GetMapping("/nuevo")
	public String crearFormulario(Model model) {
		Director nuevoDirector = new Director();
		model.addAttribute("nuevoDirector", nuevoDirector);

		return "directorForm";
	}
	
	@PostMapping ("/nuevo/submit")
	public String submitNuevo(@ModelAttribute Director nuevo) {
		dr.save(nuevo);
		return "redirect:/directores";
	}
	
	@GetMapping ("eliminar/{id}")
	public String initDelete(@PathVariable ("id") int id) {
		dr.deleteById(id);
		return "redirect:/directores";
		
	}
}
